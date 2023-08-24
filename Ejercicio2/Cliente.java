/*
Kevin Josué Villagrán Mérida
Ejercicio #2 
Fecha de creación: 24/08/2023 1:04
Fecha de ultima modificación: 24/08/2023 17:13
*/

public class Cliente{
    //Atributos del cliente
    private String nombre;
    private String correo;
    private int visitas;
    private int personas;
    private String tipo;
    private String[] tipos = {"Regular", "Frecuente", "VIP"};
    private boolean hospedado;

    //Metodos del cliente
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setHospedado(boolean hospedado){
        this.hospedado = hospedado;
    }

    public boolean getHospedado(){
        return hospedado;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getCorreo(){
        return correo;
    }

    public void setVisitas(int visitas){
        this.visitas = visitas;
    }

    public int getVisitas(){
        return visitas;
    }

    public void setPersonas(int personas){
        this.personas = personas;
    } 

    public int getPersonas(){
        return personas;
    }

    public String getTipo(){
        return tipo;
    }

    public Cliente(String nombre, String correo, int visitas, int personas){//Constructor
        this.nombre = nombre;
        this.correo = correo;
        this.visitas = visitas;
        this.personas = personas;

        if(visitas >= 5 && visitas < 10)//Se define el tipo segun las visitas
            tipo = tipos[1];
        else if(visitas >= 10)
            tipo = tipos[2];
        else
            tipo = tipos[0];
    }

    public String toString(){//Sirve para mostrar los datos del cliente
        return "\nDatos del cliente:" + "\nNombre: " + nombre + "\nCorreo: " + correo + "\nCantidad de personas que desea hospedar: " + personas + "\nCantidad de visitas hechas anteriormente: " + visitas + "\nTipo de cliente: " + tipo;
    }
}