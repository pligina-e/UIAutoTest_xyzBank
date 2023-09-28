# UIAutoTest_xyzBank
Автоматизированное тестирование формы входа менеджера банка на сайте [globalsqa.com](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager)
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
