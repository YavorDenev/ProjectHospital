## Явор Денев и Мартин Катев представят...
 ![Hospital](https://raw.githubusercontent.com/YavorDenev/ProjectHospital/master/HelpFiles/Hospital.JPG)
 
 **Общи изисквания**

- проектите се разработват в екипи по двама
- ползва се github с регулярни къмити от всички в екипа
- използва се ООП
- има написани Unit Tests с поне 70% code coverage
- \* има УАУ ефект (нещо, което не сме показвали на лекциите, свързано е с проекта и кара хората, които гледат проекта за кажат “УАУ”)

**Критерии за оценяване**

- правилно използване на ООП концепции: конструктори, нива на достъп, статични методи и свойства
- ООП дизайн: правилен подбор на класове и връзки между тях
- спазване на SOLID принципи
- качествен код: подходящо именуване и форматиране; спазване на добри практики
- добро взаимодействие с потребителя
- цялостност и завършено на проекта
- адекватна обработка на грешки
- Unit tests
# Болница 
Искаме да изградим софтуер за работа в болница. 

При стартиране на системата, потребителят трябва да има възможност да избере в ролята си, с която иска да я използва – лекар или пациент. Всеки лекар има уникално **doctor\_id**. Всеки пациент има уникално **patient\_id**. Всеки запазен час за посещение има уникално **appointment\_id**. 

Данните за категориите са записани в отделни файлове и трябва да се зареждат при стартиране на програмата от предварително известни .csv файлове. За целта на този проект ще работим с **три вида .csv файла**, чиято структура ще е следната: 

1. Данни за пациентите (във файл patients.csv): 

`       `patient\_id,   first\_name,   last\_name,   age

Пример за файл:

1,Maria,Petrova,25

2,Ivan,Ivanov,34

3,Konstantin,Marinov,18

4,Krasimira,Petkova,4

1. Данни за лекарите (във файл doctors.csv):

`      `doctor\_id,   first\_name,   last\_name,   specialty

Пример за файл: 

1,Ivaylo,Petrov,45,urology

2,Tsvetomira,Borisova,gynecology

3,Krasen,Borislavov,anesthesiology

Специалностите са следните:

- Анестезиология (Anesthesiology)
- Вътрешни болести (Internal diseases)
- Гастроентерология (Gastroenterology)
- Ендокринология (Endocrinology)
- Кардиология (Cardiology)
- Дерматология (Dermatology)
- Неврология (Neurology)
- Нефрология (Nephrology)
- Психиатрия (Psychiatry)
- Ревматология (Rheumatology)
- Гинекология (Gynecology)
- Ортопедия (Orthopedics)
- Очни болести (Ophthalmology)
- Урология (Urology)
- Хирургия (Surgery)

1. Данни за записаните часове (във файл appointments.csv):

`       `appointment\_id,    patient\_id,    type\_of\_examination,    date,    time,   doctor\_id 

Пример за файл: 

1,1,initial,28-06-2021,1000,2

2,5,secondary,21-06-2021,1230,5

3,7,consultation,15-06-2021,1030,2

4,1,initial,15-06-2021,1030,4

5,3,procedure,15-06-2021,1030,2

Прегледите могат да бъдат от следните типове:

- Първичен преглед (initial)
- Вторичен преглед (secondary)
- Консултация (consultation)
- Медицинска процедура (procedure)


Важно е да се отбележи, че id-тата (данните в първото поле) са уникални само в рамките на текущия файл – няма как да имаме два записани часа за преглед с appointment\_id = 1 например, но пациент 1 може да си е записал повече от един час. Данните във файловете винаги ще бъдат коректно подавани. 


Вход като лекар:

- Системата подканва лекаря да въведе своето id и име 
- При неуспешен вход, потребителят отново се приканва да въведе своите данни до успешно въведена комбинация
- При успешен вход (лекар с такива данни съществува в системата) пред лекаря се поставя възможността за избор от меню с опции
- Системата да дава възможност за следните операции: 
  - **Визуализиране** на всички запазени часове от пациентите за даден лекар (ако не е въведено doctor\_id, по подразбиране се извежда информация за текущия лекар, влязъл в системата)
  - **Сортиране** на всички запазени часове на даден лекар (ако не е въведено doctor\_id, по подразбиране се извежда информация за текущия лекар, влязъл в системата) по:
  - име на пациента (във възходящ и низходящ ред)
  - записан час за преглед на пациента (във възходящ и низходящ ред)
  - пациентско ид (във възходящ и низходящ ред)

Системата трябва да поддържа опцията за **групиране на пациентите** по:

- име на специалиста, при който са запазили час
- отделение
- дата на посещение



Вход като пациент:

- Системата подканва потребителя да въведе своето уникално patient\_id и първото си име 
- При неуспешен вход, потребителят отново се поканва да въведе своите данни до успешно въведена комбинация
- При успешен вход (пациент с такова потребителско име и парола съществува), 

на потребителя се дава възможност за следните операции: 

- Визуализация на всички записани часове за текущия пациент
- Промяна на датата/часа на записан час (по подадено appointment\_id)
- Отказване от записан час (по подадено appointment\_id)

# Реализираното от нас:
 ![DefaultUserAllowedActions](https://github.com/YavorDenev/ProjectHospital/blob/master/HelpFiles/DefaultUserAllowedActions.PNG)
