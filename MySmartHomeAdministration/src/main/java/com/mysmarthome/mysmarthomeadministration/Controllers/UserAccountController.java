package com.mysmarthome.mysmarthomeadministration.Controllers;

import com.mysmarthome.mysmarthomeadministration.Entites.UserAccount;
import com.mysmarthome.mysmarthomeadministration.Services.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

    @Autowired
    IUserAccountService userAccountService;

    public boolean passwordComplexity(String password) throws Exception {
        int counter = 0;
        boolean control = true;
        boolean uppercase = false;
        boolean lowercase = false;
        boolean special = false;
        boolean number = false;
        String specials = "@+-=*/.&µ%$}])|[({,!:?\";";
        String numbers = "0123456789";
        String lowercases="azertyuiopqsdfghjklmwxcvbn";
        //On vérifie la longueur du mot de passe en premier
        if(password.length()<8) {
            control =false;
            throw new Exception("Le mot de passe doit faire au moins 8 caractères");
        }
        else {
            while(counter<password.length() && (uppercase==false || lowercase==false || number==false || special==false)) {
                //on génère une chaine de caractère contenant le caractère à la position compteur
                String sequence = password.substring(counter, counter+1);
                //On vérifie les 4 critères
                if(number==false) {
                    if(numbers.contains(sequence)) number=true;
                }
                if(lowercase==false) {
                    if(lowercases.contains(sequence)) lowercase=true;
                }
                if(uppercase==false) {
                    String majuscules = lowercases.toUpperCase();
                    if(majuscules.contains(sequence)) uppercase=true;
                }
                if(special==false) {
                    if(specials.contains(sequence)) special=true;
                }

                counter++;
            }

            if(uppercase==false || lowercase==false || number==false || special==false) {
                control=false;
                if(uppercase==false) throw new Exception("Le mot de passe doit contenir au moins une majuscule");
                if(lowercase==false) throw new Exception("Le mot de passe doit contenir au moins une minuscule");
                if(special==false) throw new Exception("Le mot de passe doit contenir au moins un caractère spécial");
                if(number==false) throw new Exception("Le mot de passe doit contenir au moins un chiffre");
            }
        }
        return control;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<UserAccount> registerUser(@RequestBody UserAccount userAccount){
        boolean test=false;
        try {
            test = passwordComplexity(userAccount.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(test==true) {
            try {
                return new ResponseEntity<>(userAccountService.register(userAccount), HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/loginBack")
    public ResponseEntity<Boolean>login(@RequestBody UserAccount userAccount) {
        try {
            return new ResponseEntity<>(userAccountService.isValid(userAccount), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}