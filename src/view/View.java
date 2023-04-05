package view;
import java.util.Scanner;

public class View{
    private Scanner input;

    public View(){
        input = new Scanner(System.in);
    }
    public void showMessage(String message){
		System.out.println(message);
	}
    public double readDouble(String message){
		this.showMessage(message);
		return input.nextDouble();
	}
    public short readShort(String message){
		this.showMessage(message);
		return input.nextShort();
	}
    public short readMenu(){
        String menuText = "Bienvenidos al Control de pacientes de Colsanitas"+"\n\n"+"1.Añadir habitación"+"\n"+"2. Ingresar paciente"+"\n"+"3.  Mostrar historial de pacientes por habitación"+"\n"+"4.  Generar XML"+"\n"+"5. Salir";
        return readShort(menuText);
    }
}