package amlet.dao;

//@DisplayName("Имплементация класса QuestionDao")
//public class QuestionDaoCsvTest {
//
//    @Autowired
//    private QuizLocalization quizLocalization;
//    private final QuestionConverter questionConverter = new QuestionConverter(quizLocalization);
//    private final String csvName = "qa_en.csv";
//
//    @Test
//    @DisplayName("метод findQuestions, прочитав csv, возвращает не пустой список")
//    void getQuestionsShouldReturnIsNotEmptyList() {
//        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName, quizLocalization, questionConverter);
//        assertFalse(questionDao.findQuestions().isEmpty());
//    }
//
//    @Test
//    @DisplayName("метод findQuestions, прочитав csv, возвращает список из 6 объектов")
//    void csvFileShouldContainsSixQuestions() {
//        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName, quizLocalization, questionConverter);
//        assertEquals(5, questionDao.findQuestions().size());
//    }
//
//    @Test
//    @DisplayName("метод findQuestions, прочитав csv, возвращает список из объектов класса Question")
//    void csvFileShouldContainsOnlyQuestionClasses() {
//        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName, quizLocalization, questionConverter);
//        questionDao.findQuestions()
//                .forEach(question -> assertEquals(question.getClass(), Question.class));
//    }
//
//    @Test
//    @DisplayName("метод findQuestions кидает CsvReadException, если csv не прочитан")
//    void getQuestionsShouldReturnIsEmptyList() {
//        QuestionDaoCsv questionDao = new QuestionDaoCsv("wrongFileName", quizLocalization, questionConverter);
//        assertThrows(CsvReadException.class, questionDao::findQuestions);
//    }
//
//}