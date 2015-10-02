#Perka Apply by API

To get your resume to the top of our stack, apply using our API by following the directions below.
You can also email your resume to [jobs@perka.com](mailto:jobs@perka.com), but that's not nearly as cool.


    Endpoint: https://api.perka.com/1/communication/job/apply

    Request Method: POST

    Data Type: JSON


##Fields
| *Attribute*	| *Description*	| *Type*	| *Required* |
| ------------- |-------------|---------|----------|
| first_name	| Your first name | String | Yes |
| last_name	| Your last name | String | Yes |
| email	| Your email | String | Yes |
| position_id | Id of position you are applying for (Don't want to be placed in a bucket? Use GENERALIST) | String | Yes |
|explanation | Tell us how you made the API request | String | No |
| projects | Links to your GitHub profile, personal projects, or other awesome things that you've done | Array of Strings | No |
| source | How did you find Perka? (avid user, talent scout, friend, Hacker School…) | String | No |
| resume | Your resume in PDF format | base64 String | Yes |


==============

####Libraries Used

* Apache Commons Codec