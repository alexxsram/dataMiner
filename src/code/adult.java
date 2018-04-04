package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class adult {
    private String age, workclass, fnlwgt, education;
    private String education_num, marital_status, occupation, relationship;
    private String race, sex, capital_gain, capital_loss;
    private String hours_per_week, native_country, annual_income;
    
    public adult(String a /*0*/, String w /*1*/, String f /*2*/, String e /*3*/, String e_n /*4*/, String m_s /*5*/, String o /*6*/, String rs /*7*/, String r /*8*/, String s /*9*/, String c_g /*10*/, String c_l /*11*/, String h_p_w /*12*/, String n_c /*13*/, String a_i /*14*/) {
        this.age = a; ///0
        this.workclass = w; ///1
        this.fnlwgt = f; ///2
        this.education = e; ///3
        
        this.education_num = e_n; ///4
        this.marital_status = m_s; ///5
        this.occupation = o; ///6
        this.relationship = rs; ///7
        
        this.race = r; ///8
        this.sex = s; ///9
        this.capital_gain = c_g; ///10
        this.capital_loss = c_l; ///11
        
        this.hours_per_week = h_p_w; ///12
        this.native_country = n_c; ///13
        this.annual_income = a_i; ///14
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setWorkclass(String workclass) {
        this.workclass = workclass;
    }
    
    public String getWorkclass() {
        return workclass;
    }
    
    public void setFnlwgt(String fnlwgt) {
        this.fnlwgt = fnlwgt;
    }
    
    public String getFnlwgt() {
        return fnlwgt;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    
    public String getEducation() {
        return education;
    }
    
    public void setEducationNum(String education_num) {
        this.education_num = education_num;
    }
    
    public String getEducationNum() {
        return education_num;
    }
    
    public void setMaritalStatus(String marital_status) {
        this.marital_status = marital_status;
    }
    
    public String getMaritalStatus() {
        return marital_status;
    }
    
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    public String getOccupation() {
        return occupation;
    }
    
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    public String getRelationship() {
        return relationship;
    }
    
    public void setRace(String race) {
        this.race = race;
    }
    
    public String getRace() {
        return race;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setCapitalGain(String capital_gain) {
        this.capital_gain = capital_gain;
    }
    
    public String getCapitalGain() {
        return capital_gain;
    }
    
    public void setCapitalLoss(String capital_loss) {
        this.capital_loss = capital_loss;
    }
    
    public String getCApitalLoss() {
        return capital_loss;
    }
    
    public void setNativeCountry(String native_country) {
        this.native_country = native_country;
    }
    
    public String getNativeCountry() {
        return native_country;
    }
    
    public void setAnnualIncome(String annual_income) {
        this.annual_income = annual_income;
    }
    
    public String getAnnualIncome() {
        return annual_income;
    }
}
