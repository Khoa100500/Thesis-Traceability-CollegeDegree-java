<h1 align="center">
    <img src="images/logo-vector-IU-01.png" alt="HCMIU Logo" width="200"/>
    <p style="text-align:center">BUILDING A SYSTEM FOR TRACING PROVENANCE OF
EDUCATIONAL CERTIFICATES
</p>
</h1>

## Prerequisites

-   Java 22
-   [Node JS and NPM](https://nodejs.org/en/download/package-manager)
-   [Maven (Optional)](https://maven.apache.org/download.cgi)
-   [MySQL Server (Please use student_tracker.sql to create schema)](https://dev.mysql.com/downloads/mysql/)
-   IntelliJ IDEA (Optional)

## Setup project

### Clone the repository

```bash
git clone https://github.com/Khoa100500/Thesis-Traceability-CollegeDegree-java.git
```

```bash
cd Thesis-Traceability-CollegeDegree-java
```

### MySQL Server

PLease run the `student_tracker.sql` script to create student_tracker schema

### Spring Boot backend

```bash
./mvnw spring-boot:run
```

### Run React frontend

#### Navigate to frontend-react sub folder

```bash
cd frontend-react
```

#### Install all npm packages

```bash
npm i
```

#### Start the application on local host

```bash
npm run dev
```

## Setup account

After running please naviagte to http://localhost:5173/register to register new lecturer account that match existing digital certificate with the following information.

|  Description  |         Test Text          |
| :-----------: | :------------------------: |
|   Username    |   Choose your user name    |
|   Password    |    Choose your password    |
|   Full Name   |      Tran Thanh Tung       |
| Email address | phamdangkhoa1005@gmail.com |

Please use pdf files in `/src/main/java/com/iupv/demo/util/Resources/` directory to upload report. For custom account please follow these steps:

1. Generate key and certificate signing request (csr)

```bash
openssl req -nodes -newkey rsa:4096 -keyout my.key -out my.csr
```

2. Log into the CAcert.org website and use the menu client certificate | New. Check Show advanced options: to show the input field Optional Client CSR. Paste the complete contents of the file my.csr (from Step_1) into the Optional Client CSR .Then check the other options and finally click on Generate Certificate.
3. Save the presented response (which is a new signed certificate) into a new file named my.crt (use Copy/Paste).
4. Please use this website https://www.sslshopper.com/ssl-converter.html to convert your certificate to PFX/PKCS#12 format.![Certificate converter](/images/Certificate%20-converter.png)
5. Make sure that your new account match the full name and email address with your certificate info.
