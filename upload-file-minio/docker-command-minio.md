docker run -d -p 9000:9000 -p 9001:9001 -e "MINIO_ROOT_USER=dungnguyen" -e "MINIO_ROOT_PASSWORD=password" quay.io/minio/minio server /data --console-address ":9001"