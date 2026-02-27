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

$sql    = "SELECT idespecialidad, nombre, descripcion, area FROM especialidades ORDER BY idespecialidad";
$result = $conn->query($sql);

$especialidades = [];
while ($row = $result->fetch_assoc()) {
    $especialidades[] = $row;
}

$conn->close();
echo json_encode($especialidades, JSON_UNESCAPED_UNICODE);
?>
