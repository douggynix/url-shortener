### Run the project
This project can be run using maven with :
```shell
mvn spring-boot:run
```

Some verification like "URL validation" is not implemented here to keep things simple. 

**LICENSE WARNING!!!!!!!!!**
**This code must not be used outside of its cope here which is for the sole purpose of the interview. It is forbidden to use this material as it is stated by its owner!!!!!!**

The Server is listening on port 8080 by default:
There is a postman file at the root of the project with **postman** folder.

#### Create short url API:
![image](https://github.com/user-attachments/assets/c82be8a3-986d-4054-92b4-16eb50943f29)

#### Retrieve full url with short url :
![image](https://github.com/user-attachments/assets/9dbf0db0-4951-43a5-b869-9ad86b81aa96)

### Springboot SQL logs and Caching
JPA  sql log query is enabled to verify is caching working properly:

![image](https://github.com/user-attachments/assets/afafb9cc-599e-4017-a63e-92c7181b377b)

### Database Console Viewer
The H2 Database console viewer is available at this adress
http://localhost:8080/h2-console/

Look for for this string below for the jdbc url in SpringBoot start logs:

![image](https://github.com/user-attachments/assets/adf28493-fcec-4149-a52c-e50518970baf)

Then replace it with it. This is an important steps for any operating system you're using such as Linux, Unix, MacOS or Windows.

![image](https://github.com/user-attachments/assets/53348f19-3610-43e9-b14f-30a22b978880)

Here is the Database Content if the application ever gets restarted

![image](https://github.com/user-attachments/assets/a0cf8b84-2eb1-4ad5-ad9c-a5aaee1f3c97)






