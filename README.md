# Spring-Boot-Upload-Multiple-Files-to-AWS-S3-Bucket
Please Check Readme

############ TO RUN ############# 
Add AWS S3 Bucket Keys to application.properties

############ SAMPLE CURL ############# 
curl --location --request POST 'http://localhost:8080/file-upload' \
--header 'entity-identifier: testEntity' \
--header 'user-id: testUser' \
--form 'files=@"/home/harshilchoudhary/Project_Files/Test files for AWS Upload/Dog.jpg"' \
--form 'files=@"/home/harshilchoudhary/Project_Files/Test files for AWS Upload/Monkey.jpg"' \
--form 'files=@"/home/harshilchoudhary/Project_Files/Test files for AWS Upload/Battery_3GP.3gp"' \
--form 'files=@"/home/harshilchoudhary/Project_Files/Test files for AWS Upload/Mouse.jpg"' \
--form 'files=@"/home/harshilchoudhary/Project_Files/Test files for AWS Upload/Cow.jpg"'

############ SAMPLE RESPONSE ############# 
{
    "requestId": "e058bd31-e056-4068-be31-adda73335816",
    "mediaDTOList": [
        {
            "id": "image_testEntity_testUser_18:50:02.887482713_1",
            "entityIdentifier": "testEntity",
            "userId": "testUser",
            "url": "https://s3.ap-south-1.amazonaws.com/spring-image-aws-storage/testFolder/image_testEntity_testUser_18%3A50%3A02.887482713_1",
            "type": "image"
        },
        {
            "id": "image_testEntity_testUser_18:50:02.887482713_2",
            "entityIdentifier": "testEntity",
            "userId": "testUser",
            "url": "https://s3.ap-south-1.amazonaws.com/spring-image-aws-storage/testFolder/image_testEntity_testUser_18%3A50%3A02.887482713_2",
            "type": "image"
        },
        {
            "id": "video_testEntity_testUser_18:50:02.887482713_3",
            "entityIdentifier": "testEntity",
            "userId": "testUser",
            "url": "https://s3.ap-south-1.amazonaws.com/spring-image-aws-storage/testFolder/video_testEntity_testUser_18%3A50%3A02.887482713_3",
            "type": "video"
        },
        {
            "id": "image_testEntity_testUser_18:50:02.887482713_4",
            "entityIdentifier": "testEntity",
            "userId": "testUser",
            "url": "https://s3.ap-south-1.amazonaws.com/spring-image-aws-storage/testFolder/image_testEntity_testUser_18%3A50%3A02.887482713_4",
            "type": "image"
        },
        {
            "id": "image_testEntity_testUser_18:50:02.887482713_5",
            "entityIdentifier": "testEntity",
            "userId": "testUser",
            "url": "https://s3.ap-south-1.amazonaws.com/spring-image-aws-storage/testFolder/image_testEntity_testUser_18%3A50%3A02.887482713_5",
            "type": "image"
        }
    ]
}

############ ABOUT #############
The Spring App takes an entity identifier and a 

