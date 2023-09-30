# UIAutoTest_xyzBank
Автоматизированное тестирование формы входа менеджера банка на сайте [globalsqa.com](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager)
## Используемые инструменты:
1. Язык программирования *Java 17*
2. IntelliJ IDEA Community Edition 2022.3
3. Selenium WebDriver (браузер Chrome, Edge)
4. Тестовый фреймворк *TestNG*
5. Сборщик *Maven*
6. Паттерн проектирования *Page Object Model* и *Page Factory*
7. Библиотека *Datafaker*
8. Библиотека *WebDriverManager*
9. Библиотека *Lombok*
10. Фреймворк *Allure*
11. Генератор документации *Javadoc*
## Чек-лист
#### Объект тестирования:
[https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager)
#### Чек-лист:
1. Создание клиента (Customer)
2. Сортировка клиентов по имени (First Name)
3. Поиск клиента
## Тест-кейс. Создание клиента (Customer)
#### Предусловие:
1. Открыть браузер
2. Перейти на страницу: [https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager)
#### Шаги:
1. Нажать на кнопку **Add Customer**
2. Заполнить поле **First Name** произвольной строкой
3. Заполнить поле **Last Name** произвольной строкой
4. Заполнить поле **Post Code** произвольной строкой
5. Нажать кнопку **Add Customer**
#### Ожидаемый результат:
Появилось модальное окно alert с сообщением: *Customer added successfully with customer id*
## Тест-кейс. Сортировка клиентов по имени (First Name)
#### Предусловие:
1. Открыть браузер
2. Перейти на страницу: [https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager)
#### Шаги:
1. Нажать на кнопку **Customers**
2. Нажать на поле таблицы **First Name**
#### Ожидаемый результат:
Строки таблицы отсортированы в алфавитном порядке по имени
## Тест-кейс. Поиск клиента
#### Предусловие:
1. Открыть браузер
2. Перейти на страницу: [https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager)
#### Шаги:
1. Нажать на кнопку **Customers**
2. Ввести в поле **Search Customers** произвольную строку
#### Ожидаемый результат:
В таблице остались строки, содержащие введённую строку
## Логика проекта
Класс **BaseTest** содержит все общие функции и переменные тестовых классов. Он с помощью класса **PropertyProvider** обращается к *property-файлу*, хранящему пути.
Это удобно, так как можно в одном месте изменить ссылку тестируемой страницы, время ожидания и др.

Класс **BasePage** содержит инициализацию *PageFactory.initElements*, которая распространяется на все дочерние классы.

Класс **Wait** содержит явные ожидания, а также некоторые методы для работы с веб-элементами страниц (Напр., *click()*, *sendKeys()*, др.).

Класс **BankManagerLoginTest** наследуется от **BaseTest**, и в нем создан экземпляр класса **Generator**, который создает случаные данные благодаря библиотеке Faker.
Также используется **@DataProvider** для передачи параметров в тестовую функцию *correctDataAutoTest*.
В классе **BankManagerLoginTest** создано три теста, соответствующие трем тест-кейсам.

Класс **AddCustomerPage** наследуется от **BasePage**, и в нем объявлены веб-элементы и методы страницы. Данный класс предназначен для работы с формой создания клиента (Первый тест-кейс).

Класс **CustomersListPage** наследуется от **BasePage**, и в нем объявлены веб-элементы и методы страницы. Данный класс предназначен для работы с формой просмотра таблицы клиентов (Второй, третий тест-кейс).
## Дополнительное задание №1. Реализация формирования отчетов Allure
Было реализовано формирование отчетов Allure. В файле *pom.xml* прописаны специальные разделы сборки.
#### Необходимые команды для генерации отчетов:
###### Первый вариант
1. В окне *Maven* (справа сбоку в IDE) дважды щелкните левой кнопкой мыши по пункту **clean** выпадающего списка *Lifecycle*;
2. Запустите тест *BankManagerLoginTest*, перейдя на вкладку с кодом, выбрав в качестве Run Конфигуратора опцию *Current File* и нажав *Shift+F10*;
3. В окне *Maven* дважды щелкните левой кнопкой мыши по пункту **allure:serve** выпадающего списка *Plugins*->*allure*.
###### Второй вариант
`mvn clean test` - проведите тесты

`mvn allure:serve` - создать отчет
## Дополнительное задание №2. Реализация параллельного запуска тестов
Для параллельного выполнения тестов в *TestNG* мы можем использовать атрибут **parallel** в *testng.xml*.

Укажем в качестве атрибута **parallel** тега suite значение **tests**, благодаря которому все тесты, находящиеся в теге *\<test>* файла *testng.xml*, будут выполняться параллельно. Значение количества потоков **thread-count** играет важную роль, поскольку, если количество потоков меньше числа тестов, тестам придется ждать выполнения других тестов. Напишем два тега *\<test>*, в которых укажем тестовый класс **BankManagerLoginTest** и **TestForParallelism**.

Класс **TestForParallelism** был создан специально для этого задания. Он представляет собой запуск теста, основанного на третьем тест-кейсе, в браузере Edge. 
Таким образом, параллельно запускаются два тестовых класса, один в браузере *Chrome*, другой в браузере *Edge*.
###### Примечание
Можно указать в качестве атрибута **parallel** тега suite значение **methods**, тогда все методы тестового класса с аннотацией *@Test* будут выполняться параллельно (Не забываем оставить один тег *\<test>* файла *testng.xml*, указав тот тестовый класс, методы которого с аннотацией *@Test* надо запустить параллельно).
## Автор
Работу выполнила *Плигина Эвелина*
