/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Kudo
 */
public class LoginBean {

    private String _loginID;
    private String _password;

    public LoginBean() {

    }

    public String getLoginID() {
        return this._loginID;
    }

    public void setLoginID(String _loginID) {
        this._loginID = _loginID;
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
}
