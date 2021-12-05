# crypto

Packaging : 
mvn clean install

Run on server : 
java -jar target/CryptoWeb-0.0.1-SNAPSHOT.jar server

REST API Documentation

Base Url - http://localhost:8080

-Homepage

    GET
    /crypto
  
-PushAndRecalculate : Takes a number as path param and returns the running mean and stand deviation
  
    PUT
    /crypto/pushAndRecalculate/{value}
  
-PushRecalculateAndEncrypt : Takes a number as path param, calculates the running mean and standard deviation, and returns them as an encrypted string

    PUT
    /crypto/pushRecalculateAndEncrypt/{value}
  
-Decrypt : Takes a "URL Encoded" string as query param and returns the decrypted value

    GET
    /crypto/decrypt?value=
  
-GenerateKey : Takes a password and salt as query params and returns a secret key

     GET
    /crypto/generateKey?password= & salt=
  
-GenerateIV : Generates an initialization vector for encryption

    GET
    /crypto/generateIV
  
  
  
  
