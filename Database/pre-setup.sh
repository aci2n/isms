#run after installing (sudo apt install mysql-server)

echo 'create database isms; create user isms_user identified by '\''isms'\''; grant all on isms.* to isms_user;' | mysql -u root -p
