package med.voll.api.infra.errors;



public class IntegrityValidation extends RuntimeException{


    public IntegrityValidation(String message){
        super((message));

    }

}
