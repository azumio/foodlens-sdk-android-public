# FoodLens SDK

The framework for recognizing food

## Getting Started


### Installing

TBD

### Start using

For the first time launch you have to obtain access token from the designated web services. And start the SDK like 

```
 FoodLens.authorizeInstance(
                    CLIENT_ID,
                    CLIENT_SECRET,
                    onAuthorized = { foodLens: FoodLens?, exception: Exception? ->
                        foodLens?.launchCameraActivityForResult(this)

                    })
```

Next time you have to obtain the last authorized instance. The instance is preserved between app launches

```
 FoodLens.lastAuthorizedInstance?.let {
                it.launchCameraActivityForResult(this)
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
