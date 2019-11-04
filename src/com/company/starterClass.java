package com.company;


public class starterClass{
    public static String usernameLogin;
    functions start = new functions();

    public void start(){
        start.openBrowser();
        start.selectElementInDropdownByValue( "dynSo", "TESTER 11");
        //login
        //start.clickById(obj,"dynLoginSbmt");
        start.clickPath( "dynLoginSbmt");
        //Select branch Hitta länk direkt till branch https://www.aktors.ee/forex-demo/index.xhtml
        start.clickPath("ucform:selbranch:0");
        //Press OK
        start.clickPath("ucform:btnOK");
        //login to Branch
        start.clickPath("ucform:btnlogin");
        //If someone else is logged in, kick them out
        tryClick("ucform:activeSession:affirmBtn");
        //If session  active
        tryClick("ucform:restoreSession:affirmBtn");
        //Logged in

    }
    private void tryClick(String id){
        if (start.isExisting(id) && id =="ucform:activeSession:affirmBtn") {
            try {
                Thread.sleep(1000);
                start.clickPath(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (start.isExisting(id) && id =="ucform:restoreSession:affirmBtn") {
            try {
                Thread.sleep(1000);
                start.clickPath(id);
                start.clickPath("ucform:sessionTotal:btn-delete-all-transactions");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                start.clickPath("ucform:sessionTotal:confirm-reverse-all:affirmBtn");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
