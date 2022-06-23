package ru.amlet.service;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amlet.entity.Author;
import ru.amlet.exception.AuthorityException;
import ru.amlet.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final PermissionService permissionService;

    public AuthorServiceImpl(AuthorRepository authorRepository, PermissionService permissionService) {
        this.authorRepository = authorRepository;
        this.permissionService = permissionService;
    }

    @Override
    @Transactional
    public Author createAuthor(String name, Authentication authentication) {
        Author author = authorRepository.save(new Author(name));
        String authority = getAuthority(authentication);
        permissionService.addPermissionForAuthority(author, BasePermission.ADMINISTRATION, authority);
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @PostFilter("hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'ADMINISTRATION')")
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @PostFilter("hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'ADMINISTRATION')")
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void updateAuthor(long id, String name) {
        authorRepository.save(new Author(id, name));
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.deleteAllById(List.of(id));
    }

    private String getAuthority(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new AuthorityException("Didn't find any authority"));
    }

}
