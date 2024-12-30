# Receipt Processor

## To Run the program

Step 1 : Build the docker

``docker build -t receipt-processor:latest .``

Step 2: Run the docker

`docker run -p 8080:8080 receipt-processor:latest`

Step 3: Serve the API

`post` receipt payload to `localhost:8080/receipts/process`