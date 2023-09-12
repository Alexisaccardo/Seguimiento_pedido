import java.sql.*;
import java.util.Scanner;

public class seguimiento {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*****BIENVENIDOS*****");

        boolean inicio = true;

        while (inicio) {

            System.out.println("1. Realizar seguimiento al pedido numero de codigo: ");
            System.out.println("2. Reportar novedad frente al pedido: ");
            System.out.println("3. Actualizar el estado del pedido: ");
            System.out.println("4. Actualizar informacion del pedido: ");
            System.out.println("5. Eliminar pedido");
            System.out.println("6. Terminar");

            System.out.println("Ingrese un numero entre 1 - 6: ");
            int respuesta = Integer.parseInt(scanner.nextLine());

            switch (respuesta) {

                case 1:
                    System.out.println("Ingrese el numero de guia: ");
                    String guia = scanner.nextLine();

                    Select_One(guia);

                    break;

                case 2:
                    System.out.println("Ingrese el numero de guia: ");
                    guia = scanner.nextLine();

                    System.out.println("Ingrese la novedad del pedido: ");
                    String novedad = scanner.nextLine();

                    Editar(guia, novedad);
                    break;

                case 3:
                    System.out.println("Ingrese el numero de guia: ");
                    guia = scanner.nextLine();

                    System.out.println("Actualice el estado del pedido: ");
                    String estado = scanner.nextLine();

                    Editar_estado(guia, estado);
                    break;

                case 4:
                    System.out.println("Ingrese el numero de guia: ");
                    guia = scanner.nextLine();

                    System.out.println("Ingrese el nombre del producto actualizado: ");
                    String producto = scanner.nextLine();

                    System.out.println("Ingrese nombre del cliente actualizado: ");
                    String cliente = scanner.nextLine();

                    System.out.println("Ingrese la dirrecion actualizada: ");
                    String direccion = scanner.nextLine();
                    
                    Editar_info(guia, producto, cliente, direccion);
                    break;

                case 5:
                    System.out.println("Ingrese el numero de guia que desea eliminar: ");
                    guia = scanner.nextLine();

                    Eliminar(guia);
                    break;

                case 6:
                    System.out.println("Finalizando...");

                    inicio = false;

                    break;

                default:
                    System.out.println("Ingrese un numero valido");
            }
        }


        }

    private static void Eliminar(String guia) throws SQLException, ClassNotFoundException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/seguimientos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM envios WHERE numero_guia = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, guia);

        int filasActualizadas = prepareStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Producto eliminado de manera exitosa");
        } else {
            System.out.println("No se encontró un numero de guia para eliminar");
        }
    }

    private static void Editar_info(String guia, String producto, String cliente, String direccion) throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/seguimientos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE envios SET producto = ?, cliente = ?, direccion = ? WHERE numero_guia = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, producto);
        preparedStatement.setString(2, cliente);
        preparedStatement.setString(3, direccion);
        preparedStatement.setString(4, guia);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se actualizo su informacion de manera exitosa");
        } else {
            System.out.println("No se encontró el numero de guia para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }
    

    private static void Editar_estado(String guia, String estado) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/seguimientos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE envios SET estado = ? WHERE numero_guia = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, estado);
        preparedStatement.setString(2, guia);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se registro el estado de manera exitosa");
        } else {
            System.out.println("No se encontró el numero de guia para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void Editar(String guia, String novedad) throws SQLException, ClassNotFoundException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/seguimientos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE envios SET novedad = ? WHERE numero_guia = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, novedad);
        preparedStatement.setString(2, guia);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se registro su novedad de manera exitosa");
        } else {
            System.out.println("No se encontró el numero de guía para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }


    private static void Select_One(String guia) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/seguimientos";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM envios WHERE numero_guia = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, guia); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String numero_guia = resultSet.getString("numero_guia");
            String producto = resultSet.getString("producto");
            String cliente = resultSet.getString("cliente");
            String estado = resultSet.getString("estado");
            String direccion = resultSet.getString("direccion");

            System.out.println("Este es el numero de guia a seguir: " + numero_guia + " estado: " + estado);

        } else {
            System.out.println("No se encontró un registro con el codigo especificado.");
        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();


    }
}