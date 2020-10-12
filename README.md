# FoodLens SDK

The framework for recognizing food

## Getting Started


### Installing

```
repositories {
 maven {
         url = "https://maven.pkg.github.com/azumio/foodlenslibrary"
       }
}

dependencies {
    implementation 'com.azumio.android:foodlenslibrary:1.0.0'
}
```

### Start using

For the first time launch you have to obtain access token from the designated web services. And start the SDK like 

```
   FoodLens.authorizedInstance(
                        ACCESS_TOKEN,
                        onAuthorized = { foodLens: FoodLens?, exception: Exception? ->
                            foodLens?.launchCameraActivityForResult(this@AIActivity)
                            btn_ai_camera.isEnabled = true

                        })
```

Next time you have to obtain the last authorized instance. The instance is preserved between app launches

```
 FoodLens.lastAuthorizedInstance?.let {
                it.launchCameraActivityForResult(this)
            } 
```

### Obtaining access token

Example how to obtain access token:

```
  private fun getAccessToken()
    {
        val url = "https://api.foodlens.com/api2/token"
        val clientId = "GET IT AT https://dev.caloriemama.ai/"
        val clientSecret = "GET IT AT https://dev.caloriemama.ai/"
        val userId =  UUID.randomUUID().toString()

        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .add("grant_type", "foodapi")
            .add("client_id",clientId)
            .add("client_secret",clientSecret)
            .add("user_id",userId)
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        ...

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
               
            }

            override fun onResponse(call: Call, response: Response) {
               ...
                runOnUiThread {
                    FoodLens.authorizedInstance(
                        tokenResponse.accessToken,
                        onAuthorized = { foodLens: FoodLens?, exception: Exception? ->
                            foodLens?.launchCameraActivityForResult(this@AIActivity)
                            btn_ai_camera.isEnabled = true

                        })
                        ...
                }

            }
        })
    }
```


### Calling FoodLens UI

#### CameraActivity

The camera activity helps to make food photo, display and confirm results.

```
foodLens.launchCameraActivityForResult()
```

The result of the recognized food image is returned as an activity result with requestCode FOODLENS_CAMERA_ACTIVITY_RESULT_CODE

```

 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == FoodLens.FOODLENS_CAMERA_ACTIVITY_RESULT_CODE)
        {
            data?.getStringExtra(FoodLens.FOODLENS_FOOD_CHECKIN)?.let {}
        }   
```

#### Data Structure
The result is a Json format containing following structure
```
"food_logs": List of logged food items
"foodrecognition_imagecache_id": Unqiue recognized image cache id
"foodrecognition_trace_segments": List of segments containing the position of the food items in the image
"nutrition": JSON Map containing summation maconutrient information
"photos" :Local/Server path to recoginized food image
"remoteid" : Unique identifier for the recogized session
"timestamp" : Unix timestamp 
"timezone" : Timezone of the user

```


## Examples

#### :hammer_and_wrench: AIActivity

Demonstrates camera activity features



## License

TBD

## Copyright

Copyright © 2020 Azumio Inc. All rights reserved.
