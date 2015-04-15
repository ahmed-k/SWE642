package spec

import geb.Page

/**
 * Created by alabdullahwi on 4/8/2015.
 */
class SurveyPage extends Page {
   static at = { $('h1').text() == 'Welcome To CS Department Homepage'  }

   static content = {
      _url     { $("form").url }
      submit   { $("input", type: "submit") }
   }
   def propertyMissing(prop) {
      $("form")."${prop}"
   }


}
