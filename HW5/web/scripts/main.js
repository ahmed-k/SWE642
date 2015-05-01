//namespaces 
var App = {};
var Cookies = {}
var SurveyMath = {};
var EventHandlers = {};
var Error = {};
var Validator = {};
var Constructors = {};
Constructors.Validators = {};
Constructors.NodeObjects = {};

//constructor functions 
Constructors.Error = function() {
    var isPending=false;
    var text = "";
    function flushText() {
        var retv = text;
        text = "";
        setIsPending(false);
        return retv;
    }
    function appendText(errText) {
        text += errText+"\n";
        setIsPending(true);
    }
    this.collect = function(error) {
        appendText(error);
    }
    this.throwInvalidDataContent = function(errType,el,ind) {
        if (errType === "number") {
            appendText("Invalid Data Input: Please enter 10 numbers"+
            " between 0 and 100") ;
        }
        else if (errType === "range") {
            appendText("Input number " + ind +" Out Of Range: "+el);
        }
    };
    function getIsPending() {
        return isPending;
    };
    function setIsPending(val) {
        if ((val === true) || (val === false))  {
            isPending = val;
        }
    };
    this.flush = function() {
        var retv=false;
        if (getIsPending()) {
            alert(flushText());
            retv = true;
        }
        return retv;
    };
    this.notify = function(valResults) {
        valResults.map(function(el) {
            if (el !== true) {
                Error.collect(el);
            }
        });
    };
};

Constructors.Validators.Selector = function(_type,_min) {
    var min = _min;
    var type;
    if(_type === 'checkbox') {
        type = "checkboxes";
    }
    else if (_type === 'radio') {
        type = 'radio buttons';
    }
    function val(obj) {
        if (obj.numberOfSelectedInputs < min) {
            return obj.name + " You must select at least " + min + " " + type;
        }
        return true;
    };
    this.validate = function(obj) {
        return val(obj);
    }

};
Constructors.Validators.Text = function(_regex,_error) {
    var regex = new RegExp(_regex);
    var error = _error;
    function val(obj, identifier) {
        if (regex.test(obj.node.value) == false) {
            var _oldval = obj.node.value;
            //clear node value
            obj.node.value="";
            return obj.name + " "+ error +", You entered: " + _oldval;
        }
        else {
            return true;
        }

    };
    this.validate = function(obj) {
        return val(obj);
    };
}

Constructors.Validators.Repository = function() {
    var _validators = {};
    var alphabetOnly = new Constructors.Validators.Text('^[a-zA-Z]*$',
        "must contain only letters");
    var alphaNumericOnly = new Constructors.Validators.Text('^[a-zA-Z0-9]*$',
        "must contain only letters or numbers");
    var zipValidator = new Constructors.Validators.Text('^\\d{5}$',
        "must contain 5 digits only");
    var campusValidator = new Constructors.Validators.Selector('checkbox', 2);
    var recommendationValidator = new Constructors.Validators.Selector('radio', 1);
    var emailValidator = new Constructors.Validators.Text('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,4}$', "email address format is not valid");

    _validators['alphabet'] = alphabetOnly; ;
    _validators['alphaNumeric'] = alphaNumericOnly;
    _validators['zipRegex'] = zipValidator;
    _validators['emailRegex'] = emailValidator;
    _validators['checkbox'] = campusValidator;
    _validators['radio'] = recommendationValidator;

    function getValidationFor(validationType) {
        return _validators[validationType];
    }
    this.validate = function(obj) {
        var _validator = getValidationFor(obj.validationType);
        return _validator.validate(obj);
    }
};

Constructors.NodeObjects.Selector = function(container,_type) {
    var _inputs = container.getElementsByTagName('input');
    var retv = {};
    retv.name = _inputs[0].parentNode.textContent;
    retv.inputs = [];
    retv.numberOfSelectedInputs = 0;
    retv.validationType = _type;
    for (var i = 0; i<_inputs.length; i++) {
        if (_inputs[i].type === _type) {
            retv.inputs.push(_inputs[i]);
            if (_inputs[i].checked) {
                retv.numberOfSelectedInputs++;
            }
        }//if
    }//for
    return retv;
};

Constructors.NodeObjects.Text = function(container,target,vtype) {
    var _node = container.getElementsByTagName('input')[target];
    this.node = _node;
    this.name = _node.parentNode.textContent;
    this.validationType = vtype;
};


//Cookies namespace
Cookies.nameCookie= "swe642name";
Cookies.retrieveName = function() {
    var retv;
    if (document.cookie) {
        var _cookie = unescape(document.cookie);
        var _tokens = _cookie.split(";");
        _tokens.forEach( function(element,index,arry) {
            var _elTokens = element.split("=");
            console.log(_elTokens);
            if (_elTokens[0].trim() === Cookies.nameCookie ){
                if (_elTokens[1] !== "null") {
                    retv = _elTokens[1];
                }
            }
        });
    }

    //if retv is not found in document.cookie
    if (retv == undefined) {
        retv = window.prompt("Please enter your name", "Ahmed");
        document.cookie = Cookies.nameCookie + "=" + escape(retv);
    }
    return retv;
};
Cookies.clearName = function() {
    var date = new Date();
    document.cookie = Cookies.nameCookie+"="+ "null;" + " expires="+date;
};

//SurveyMath namespace
SurveyMath.average = function(numbers) {
    var avg = 0.0;
    var sum = 0.0;
    numbers.forEach(function(el) {
        sum += el;
    });
    avg = sum / numbers.length;
    return avg;
};
SurveyMath.maximum = function(numbers) {
    var max = -Infinity;
    numbers.forEach(function (el, ind, arr) {
        if (el > max){
            max = el;
        }
    });
    return max;
};

//Error namespace
Error = new Constructors.Error();
Validator = new Constructors.Validators.Repository();

//EventHandler namespace
EventHandlers.dataSanitizer  = function(event) {
    var text = event.currentTarget.value;
    var arr = text.split(",");
    var _arr = [];
    arr.forEach(function(el,ind,array) {
        var number = Number(el);
        if (number === NaN) {
            return Error.collect("Invalid Data Input: " +
            el + " is not a number.");
        }
        else if (number < 1 || number > 100) {
            return Error.collect("Invalid Data Input: " +
            "Input number " + ind + " out of range: "+  number + ". Please enter"+
            " a number betwen 1 and 100.");
        }
        //valid number
        else {
            _arr.push(number);
        }
    });
    if (Error.flush()) {
        return;
    }
    else {
        var average = SurveyMath.average(_arr);
        var max     = SurveyMath.maximum(_arr);
        var _nodeAvg = document.getElementsByName("average")[0];
        var _nodeMax = document.getElementsByName("maximum")[0];
        _nodeAvg.value = average;
        _nodeMax.value = max;
    }
};




EventHandlers.formSanitizer = function(formEvent) {
    formEvent.preventDefault();
    var form = formEvent.currentTarget;

    function validateNodes(nodeArray) {
        var results = nodeArray.map(Validator.validate);
        return results;
    };

    function validateName(form) {
        var firstName = new Constructors.NodeObjects.Text(form, 'studentBean.firstName', 'alphabet');
        var lastName = new Constructors.NodeObjects.Text(form,'studentBean.lastName', 'alphabet');
        var result = validateNodes([firstName, lastName]);
        Error.notify(result);
    };

    function validateAddress(form) {
        var street = new Constructors.NodeObjects.Text(form, 'studentBean.street', 'alphaNumeric');
        var city = new Constructors.NodeObjects.Text(form, 'studentBean.city', 'alphabet');
        var zip  = new Constructors.NodeObjects.Text(form, 'studentBean.zip', 'zipRegex');
        var result = validateNodes([street, city, zip]);
        Error.notify(result);
    };
    function validateEmail(form) {
        var email = new Constructors.NodeObjects.Text(form, 'studentBean.email', 'emailRegex');
        var result = validateNodes([email]);
        Error.notify(result);
    };

    function validateCheckboxes(form) {
        var checkboxes = new Constructors.NodeObjects.Selector(form, 'checkbox');
        var result = validateNodes([checkboxes]);
        Error.notify(result);
    };
    function validateRadioButtons(form) {
        var radioButtons = new Constructors.NodeObjects.Selector(form, 'radio');
        var result = validateNodes([radioButtons]);
        Error.notify(result);
    };

    validateName(form);
    validateAddress(form);
    validateCheckboxes(form);
    validateRadioButtons(form);
    validateEmail(form);
    //submit if no errors found
    if (Error.flush() == false) {
        this.submit();
    }
};
EventHandlers.zipCodeLookup = function() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 ) {
            if (xmlHttp.status == 200) {
                var userZip = document.getElementsByTagName('input')['studentBean.zip'].value;
                var json = JSON.parse(xmlHttp.responseText);
                var _return = json.zipcodes.some(function(el) {
                    if (el.zip === userZip) {
                        var city = document.getElementsByTagName('input')['studentBean.city'];
                        city.value = el.city;
                        var state = document.getElementsByTagName('input')['studentBean.state'];
                        state.value = el.state;
                    }
                });
                if (_return === false) {
                    var zipError = document.getElementsByTagName('div')['zip_error'];
                    zipError.innerHTML = "Zip not found."

                }
            }//if
            else if (xmlHttp.status == 400) {
                alert('error reading zipcodes file');
            }
        }//if




    }
    xmlHttp.open('GET', 'http://mason.gmu.edu/~amuhamm3/hw3/zipcodes.json',true);
    xmlHttp.send();
}


App.registerEventListeners = function() {
/*    var _dataNode = document.getElementsByName("data")[0];
    _dataNode.addEventListener("blur", EventHandlers.dataSanitizer);*/
    var _formNode = document.getElementsByTagName("form")[0];
    _formNode.addEventListener("submit", EventHandlers.formSanitizer);
    var _zipNode = document.getElementsByTagName('input')['studentBean.zip'];
    _zipNode.addEventListener('blur', EventHandlers.zipCodeLookup);
};
App.salute = function() {
    var retv = "";
    var date = new Date();
    var hrs = date.getHours();
    if (hrs < 12) {
        retv = "Morning";
    }
    else {
        hrs = hrs - 12;
        if (hrs < 3) 	{
            retv = "Day";
        }
        else if (hrs < 6) 	{
            retv = "Afternoon" ;
        }
        else if (hrs < 9) 	{
            retv = "Evening";
        }
        else 			{
            retv = "Night";
        }
    }
    return "Good " + retv + " "  ;
};
App.greet = function() {
    var name = Cookies.retrieveName();
    var personDiv = document.getElementById('greeting');

    //create greeting text node
    var _text = App.salute() + name + ", welcome to the George Mason Department Website!"
    var _node = document.createTextNode(_text);
    personDiv.appendChild(_node);

    //create line break
    personDiv.appendChild(document.createElement('br'));

    //create wrong person link
    _node = document.createElement('a');
    _node.setAttribute('href', 'javascript:wrongPerson()');
    _node.innerText = 'Click here if you are not ' + name ;
    personDiv.appendChild(_node);
};
App.wrongPerson = function() {
    Cookies.clearName();
    location.reload();
};
App.init = function() {
    App.registerEventListeners();
    App.greet();
}

//kickoff 
window.onload = App.init();   
