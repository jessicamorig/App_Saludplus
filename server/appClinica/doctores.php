<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Origin: *");

$host     = "localhost";
$usuario  = "root";
$password = "";
$base     = "clinica_saludplus";

$conn = new mysqli($host, $usuario, $password, $base);
$conn->set_charset("utf8mb4");

if ($conn->connect_error) {
    echo json_encode(["error" => "Error de conexión: " . $conn->connect_error]);
    exit;
}

$sql = "SELECT d.iddoctor, d.nombres, d.apellidos,
               d.idespecialidad, e.nombre AS especialidad,
               d.telefono, d.correo, d.horario, d.consultorio
        FROM doctores d
        INNER JOIN especialidades e ON d.idespecialidad = e.idespecialidad
        ORDER BY e.nombre, d.apellidos";

$result = $conn->query($sql);

$doctores = [];
while ($row = $result->fetch_assoc()) {
    $doctores[] = $row;
}

$conn->close();
echo json_encode($doctores, JSON_UNESCAPED_UNICODE);
?>
