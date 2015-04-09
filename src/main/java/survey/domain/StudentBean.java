package survey.domain;

/**
 * Created by Ahmed Alabdullah on 3/29/15.
 */
public class StudentBean {

    public String firstName;
    public String lastName;
    public String street;
    public String city;
    public String zip;
    public String telephone;
    public String email;
    public String url;
    public String referralSource;
    public String[] campus;
    public String graduationMonth;
    public String recommendationLikelihood;
    public String graduationYear;
    public String additionalComments;
    public String studentID;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }

    public String[] getCampus() {
        return campus;
    }

    public void setCampus(String[] campus) {
        this.campus = campus;
    }

    public String getGraduationMonth() {
        return graduationMonth;
    }

    public void setGraduationMonth(String graduationMonth) {
        this.graduationMonth = graduationMonth;
    }

    public String getRecommendationLikelihood() {
        return recommendationLikelihood;
    }

    public void setRecommendationLikelihood(String recommendationLikelihood) {
        this.recommendationLikelihood = recommendationLikelihood;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
