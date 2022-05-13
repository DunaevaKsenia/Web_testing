# Diplom_3


В задании 3 дипломного проекта указано, что необходимо протестировать функциональность в 
Google Chrome и Яндекс.Браузере.
Запустить тесты под яндексом удалось только при помощи явного указания пути к файлу с 
яндекс драйвером в свостве "webdriver.chrome.driver". 

    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\yandexdriver.exe");

Тесты делятся на две группы - в пакете ru.yandex.stellarburgers.chrome тесты запускаются под 
Google Chrome браузером, в пакете ru.yandex.stellarburgers.yandex тесты запускаются под
Яндекс.Браузером.  

Перед запуском тестов, где это необходимо, создается User с рандомным логином, паролем и email-ом.
После прохождения тестов, если пользователь был зарегистрирован, то он удаляется.

В классе UserClient в метод deleteUser(User user, String accessToken) и logoutUser(String userEmail, Map<String, String> userData) передается параметр user, чтобы в отчете Allure
было указано имя удаляемого/выходящего из системы пользователя.

**Тесты**

Обозначение: - позитивные, * негативные

**Пакет user**

  Регистрация пользователя

    - корректного рандомного пользователя
    * пользователя с некорректным паролем (3 или 4 символа)

  Авторизация пользователя

    - вход по кнопке «Войти в аккаунт» на главной
    - вход через кнопку «Личный кабинет»
    - вход через кнопку в форме регистрации
    - вход через кнопку в форме восстановления пароля`

Выход пользователя из аккаунта

    - выход выход по кнопке «Выйти» в личном кабинете

**Пакет pages**

Регистрация пользователя

    - в разделе «Конструктор» секция по умолчанию - «Булки»
    - в разделе «Конструктор» переход из секции «Булки»и в секцию «Соусы»
    - в разделе «Конструктор» переход из секции «Булки» в секцию «Начинки»
    - в разделе «Конструктор» переход из секции «Соусы» в секцию «Булки»
    - в разделе «Конструктор» переход из секции «Соусы» в секцию «Начинки»
    - в разделе «Конструктор» переход из секции «Начинки» в секцию «Булки»
    - в разделе «Конструктор» переход из секции «Начинки» в секцию «Соусы»

 