<div style="text-align: center;">

#   **Library**
##  **WEB App**

</div>

----
### Описание приложения
Library представляет собой электронную библиотеку с возможностью аренды книг.
Писатели могут добавлять свои книги в библиотеку, а читатели арендовать доступные книги.

Используемые технологии:

* Java
* Maven
* Spring Boot
* Spring Data JPA
* Lombok
* PostgreSQL
* Docker
* Nginx
----
### Функции приложения

- Регистрация нового писателя
- Регистрация новой книги
- Регистрация нового писателя
- Аренда книги
- Возврат книги в библиотеку
----
### Установка и настройка
#### 1. Загрузите необходимые пакеты:
```
sudo apt-get update
sudo apt upgrade
sudo apt-get install docker-compose -y
sudo apt-get install nginx -y
sudo apt-get install systemd -y
sudo apt-get install openjdk-17-jdk -y
sudo apt-get install maven -y
```
#### 2. Создайте следующие директории и выдайте необходимые права:
```
sudo mkdir /home/superuser/app
sudo mkdir /var/www/app/library
sudo chmod 755 -R /var/www/app/library
```
#### 3. Перейдите в нужную директорию и клонируйте репозиторий:
```
cd /home/superuser/app
sudo git clone https://github.com/MrGreenNV/library-webapp-edu.git
cd library-webapp-edu/
```
#### 4. Осуществите сборку проекта:
```
sudo mvn install
```
#### 5. Разверните базу данных в Docker контейнере:
```
sudo docker-compose up --build -d
```
#### 6. Скопируйте jar файл для релиза:
```
sudo cp home/superuser/app/library-webapp-edu/target/webapp-1.0.jar /var/www/app/library
```
#### 7. Создайте демона, запускающего приложение:
```
sudo nano /etc/systemd/system/library.service

[Unit]
Description=Java App
After=syslog.target
After=network.target[Service]
User=superuser
Type=simple

[Service]
ExecStart=/usr/bin/java -jar /var/www/app/library/webapp-1.0.jar
Restart=always
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=library.service

[Install]
WantedBy=multi-user.target
```
#### 8. Проверьте статус демона:
```
sudo systemctl daemon-reload
sudo systemctl status library.service
```
#### 9. Измените настройки по умолчанию Nginx для настройки revers proxy:
```
sudo nano /etc/nginx/sites-available/default

server {
	listen 80;
	listen [::]:80;
	server_name _;
	
	location / {
		proxy_pass         http://127.0.0.1:5000/;
	}
}
```
#### 10. Перезапустите Nginx и проверьте его статус:
```
sudo nginx -t
sudo systemctl restart nginx
sudo systemctl status nginx
```

После запуска приложение будет доступно по адресу: http://<ip адрес сервера>:80/.

----

### Вклад и обратная связь
Если вы хотите внести свой вклад в развитие GreenChat Auth или обнаружили проблему, пожалуйста, создайте issue в репозитории проекта или отправьте pull request с вашими предложениями.
