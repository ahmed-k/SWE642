package spec

import geb.Browser

/**
 * Created by alabdullahwi on 4/8/2015.
 */

Browser.drive {

    to "localhost:7001"
    at SurveyPage
    firstName = "Ahmed"
    lastName = "Khalid"
    Street = "Farazdaq"
    zip = "22181"
    telephone = "4227715"
    email = "ahmed@test.gmail"
    _url = "http://www.ahmed.com"
    campus = ["students", "campus"]
    referralSource = "Friends"
    graduationMonth = "May"
    graduationYear = "2015"
    additionalComments = "no comments..."
    data = "1,2,3,4,5,6,7,8,99,100"
    studentID ="G0012312"
    submit.click()



}


