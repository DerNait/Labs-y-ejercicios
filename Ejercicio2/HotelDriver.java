/*
Kevin Josué Villagrán Mérida
Ejercicio #2 
Fecha de creación: 24/08/2023 1:04
Fecha de ultima modificación: 24/08/2023 17:13
*/

import java.util.*; //Se importa librerias

public class HotelDriver{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);//Scanner para introducir valores

        //Valores de cada tipo de habitacion
        float habitacionEstandarPrecio = 500f;
        int habitacionEstandarCapacidad = 4;

        float habitacionDeluxePrecio = 1000f;
        int habitacionDeluxeCapacidad = 8;
        
        float habitacionSuitePrecio = 2000f;
        int habitacionSuiteCapacidad = 16;

        //Se crean las habitaciones de cada tipo
        Habitacion[] habitacionesEstandar = 
        {
            new Habitacion("1",habitacionEstandarCapacidad,habitacionEstandarPrecio,0),
            new Habitacion("2",habitacionEstandarCapacidad,habitacionEstandarPrecio,0),
            new Habitacion("3",habitacionEstandarCapacidad,habitacionEstandarPrecio,0),
            new Habitacion("4",habitacionEstandarCapacidad,habitacionEstandarPrecio,0),
            new Habitacion("5",habitacionEstandarCapacidad,habitacionEstandarPrecio,0)
        };

        Habitacion[] habitacionesDeluxe = 
        {
            new Habitacion("6",habitacionDeluxeCapacidad,habitacionDeluxePrecio,1),
            new Habitacion("7",habitacionDeluxeCapacidad,habitacionDeluxePrecio,1),
            new Habitacion("8",habitacionDeluxeCapacidad,habitacionDeluxePrecio,1),
            new Habitacion("9",habitacionDeluxeCapacidad,habitacionDeluxePrecio,1),
            new Habitacion("10",habitacionDeluxeCapacidad,habitacionDeluxePrecio,1)
        };

        Habitacion[] habitacionesSuite = 
        {
            new Habitacion("11",habitacionSuiteCapacidad,habitacionSuitePrecio,2),
            new Habitacion("12",habitacionSuiteCapacidad,habitacionSuitePrecio,2),
            new Habitacion("13",habitacionSuiteCapacidad,habitacionSuitePrecio,2),
            new Habitacion("14",habitacionSuiteCapacidad,habitacionSuitePrecio,2),
            new Habitacion("15",habitacionSuiteCapacidad,habitacionSuitePrecio,2)
        };

        //Variable salir y seleccion del menu
        boolean salir = false;
        String seleccion;

        //Datos del cliente a crear
        String nombre;
        String correo;
        int visitas = 0;
        int personas = 0;

        //Variables para la lista
        int personasEnLista = 0;
        int recorrido = 0;
        int tipoDe = 0;
        boolean enlistado = false;

        //Se crea la lista de espera y se define la variable de cliente
        Cliente cliente = null;
        Cliente[] listaDeEspera = new Cliente[10];

        while(!salir){//Menu principal
            System.out.println("\n==== HOTEL ====");
            System.out.println("¿Que desea hacer? Introduzca el numero de la opción que desea escoger");
            System.out.println("1. Reservar habitación");
            System.out.println("2. Asignar habitación");
            System.out.println("3. Salir");
            
            seleccion = scan.nextLine();//Seleccion del usuario
            switch(seleccion){
                case "1":
                    boolean anException = false;
                    //REGISTRO DE HABITACION
                    System.out.println("\n=== RESERVAR HABITACIÓN ===");

                    System.out.println("\n==> REGISTRO DEL CLIENTE: ");//Se registran los datos del cliente
                    System.out.println("Introduzca su nombre: ");
                    nombre = scan.nextLine();
                    System.out.println("Introduzca su correo: ");
                    correo = scan.nextLine();
                    do{
                        System.out.println("¿Cuantas personas vienen con usted? Ingrese solo un valor numerico: ");//
                        try{//Se asegura que el valor introducido sea un valor numerico y no algo mas
                            personas = Integer.parseInt(scan.nextLine());
                            anException = false;
                        }catch(Exception e){//Si el usuario introduce un valor incorrecto, el ciclo se repetira hasta que coloque un valor valido.
                            System.out.println("\nIntroduzca un valor numerico valido");
                            anException = true;
                        }
                    } while(anException);

                    do{
                        System.out.println("¿Cuantas veces nos ha visitado anteriormente? Ingrese solo un valor numerico: ");//
                        try{//Se asegura que el valor introducido sea un valor numerico y no algo mas
                            visitas = Integer.parseInt(scan.nextLine());
                            anException = false;
                        }catch(Exception e){//Si el usuario introduce un valor incorrecto, el ciclo se repetira hasta que coloque un valor valido.
                            System.out.println("\nIntroduzca un valor numerico valido");
                            anException = true;
                        }
                    } while(anException);

                    cliente = new Cliente(nombre, correo, visitas, personas);//Se crea el cliente con los datos ingresados

                    if(cliente.getTipo() == "Regular")//Se define el tipo de cliente segun sus visitas
                        tipoDe = 0;
                    else if(cliente.getTipo() == "Frecuente")
                        tipoDe = 1;
                    else
                        tipoDe = 2;

                    System.out.println(cliente.toString());
                    break;
                case "2":
                    System.out.println("=== ASIGNAR HABITACIÓN ===");//Se asigna la habitación
                    if(cliente == null){
                        System.out.println("Su cliente no ha sido registrado o ya ha sido asignado a una habitacion."+"\nLos clientes en lista de espera aun no cumplen con las condiciones para ser asignados a una habitacion.");//Si no hay clientes creados se regresa al menu
                        break;
                    }

                    switch(tipoDe){
                        case 0://Segun el tipo, se mira que cumpla las condiciones de la habitacion
                            recorrido = 0;
                            enlistado = false;
                            for(Habitacion habitacion : habitacionesEstandar){
                                if(habitacion.getDisponible() == true && habitacion.ValidarEspacio(cliente.getPersonas()) == true && cliente.getHospedado() == false){
                                    habitacion.setCliente(cliente);
                                    habitacion.setDisponible(false);
                                    cliente.setHospedado(true);
                                    System.out.println("Se le ha asignado la habitación: #" + habitacion.getNumeroHabitacion() + " de tipo: " + habitacion.getTipo());
                                }
                                else
                                    recorrido++;
                                }

                            if(recorrido >= 5){//Si no cumple, se agrega a la lista de espera
                                if(personasEnLista >= listaDeEspera.length)
                                    System.out.println("No hay habitaciones disponibles ni espacios en la lista de espera, lo sentimos mucho...\n");
                                else{
                                    for(Cliente actualCliente : listaDeEspera){
                                        if(actualCliente == null && enlistado == false){
                                            actualCliente = cliente;
                                            personasEnLista++;
                                            enlistado = true;
                                        }
                                    }
                                    System.out.println("No hay habitaciones disponibles, ha sido agregado a la lista de espera...\n");                                    
                                }
                            }
                            break;
            
                        case 1://Se mira que cumpla las condiciones de la habitacion si es cliente frecuente
                            enlistado = false;  
                            recorrido = 0;
                            for(Habitacion habitacion : habitacionesEstandar){//Pasa por las habitaciones estandar
                                if(habitacion.getDisponible() == true && habitacion.ValidarEspacio(cliente.getPersonas()) == true && cliente.getHospedado() == false){
                                    habitacion.setCliente(cliente);
                                    habitacion.setDisponible(false);
                                    cliente.setHospedado(true);
                                    System.out.println("Se le ha asignado la habitación: #" + habitacion.getNumeroHabitacion() + " de tipo: " + habitacion.getTipo());
                                }
                                else
                                    recorrido++;
                                }

                            if(recorrido >= 5){
                                for(Habitacion habitacion : habitacionesDeluxe){//Pasa por las habitaciones deluxe
                                if(habitacion.getDisponible() == true && habitacion.ValidarEspacio(cliente.getPersonas()) == true && cliente.getHospedado() == false){
                                    habitacion.setCliente(cliente);
                                    habitacion.setDisponible(false);
                                    cliente.setHospedado(true);
                                    System.out.println("Se le ha asignado la habitación: #" + habitacion.getNumeroHabitacion() + " de tipo: " + habitacion.getTipo());
                                }
                                else
                                    recorrido++;
                                }
                            }

                            if(recorrido >= 10){
                                if(personasEnLista >= listaDeEspera.length)
                                    System.out.println("No hay habitaciones disponibles ni espacios en la lista de espera, lo sentimos mucho...\n");
                                else{
                                    for(Cliente actualCliente : listaDeEspera){
                                        if(actualCliente == null && enlistado == false){
                                            actualCliente = cliente;
                                            personasEnLista++;
                                            enlistado = true;
                                        }
                                    }
                                    System.out.println("No hay habitaciones disponibles, ha sido agregado a la lista de espera...\n");                                    
                                }
                            }
                            break;
                        case 2://Se mira que cumpla las condiciones de la habitacion si es cliente VIP
                            enlistado = false;
                            recorrido = 0;
                            for(Habitacion habitacion : habitacionesEstandar){//Pasa por las habitaciones estandar
                                if(habitacion.getDisponible() == true && habitacion.ValidarEspacio(cliente.getPersonas()) == true && cliente.getHospedado() == false){
                                    habitacion.setCliente(cliente);
                                    habitacion.setDisponible(false);
                                    cliente.setHospedado(true);
                                    System.out.println("Se le ha asignado la habitación: #" + habitacion.getNumeroHabitacion() + " de tipo: " + habitacion.getTipo());
                                }
                                else
                                    recorrido++;
                                }

                            if(recorrido >= 5){//Pasa por las habitaciones deluxe
                                for(Habitacion habitacion : habitacionesDeluxe){
                                if(habitacion.getDisponible() == true && habitacion.ValidarEspacio(cliente.getPersonas()) == true && cliente.getHospedado() == false){
                                    habitacion.setCliente(cliente);
                                    habitacion.setDisponible(false);
                                    cliente.setHospedado(true);
                                    System.out.println("Se le ha asignado la habitación: #" + habitacion.getNumeroHabitacion() + " de tipo: " + habitacion.getTipo());
                                }
                                else
                                    recorrido++;
                                }
                            }

                            if(recorrido >= 10){//Pasa por las habitaciones suite
                                for(Habitacion habitacion : habitacionesSuite){
                                if(habitacion.getDisponible() == true && habitacion.ValidarEspacio(cliente.getPersonas()) == true && cliente.getHospedado() == false){
                                    habitacion.setCliente(cliente);
                                    habitacion.setDisponible(false);
                                    cliente.setHospedado(true);
                                    System.out.println("Se le ha asignado la habitación: #" + habitacion.getNumeroHabitacion() + " de tipo: " + habitacion.getTipo());
                                }
                                else
                                    recorrido++;
                                }
                            }

                            if(recorrido >= 15){
                                if(personasEnLista >= listaDeEspera.length)
                                    System.out.println("No hay habitaciones disponibles ni espacios en la lista de espera, lo sentimos mucho...\n");
                                else{
                                    for(Cliente actualCliente : listaDeEspera){
                                        if(actualCliente == null && enlistado == false){
                                            actualCliente = cliente;
                                            personasEnLista++;
                                            enlistado = true;
                                        }
                                    }
                                    System.out.println("No hay habitaciones disponibles, ha sido agregado a la lista de espera...\n");                                    
                                }
                            }
                            break;
                    }
                    cliente = null; 
                    break;
                case "3"://En caso de que desee salir, se despide
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                    break;
                default:
                    System.out.println("Por favor, introduzca un valor numerico valido entre 1 a 3, presione enter para continuar...");//Si introduce algo invalido, se le dice que lo introduzca bien
                    seleccion = scan.nextLine();
                    break;
            }
        }        
    }
}