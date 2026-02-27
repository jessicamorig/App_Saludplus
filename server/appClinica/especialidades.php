<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Origin: *");

$host     = "mysql-clinicasaludplus.alwaysdata.net";
$usuario  = "clinicasaludplus_home";
$password = "saludplus123";
$base     = "clínicasaludplus_inicio";

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
