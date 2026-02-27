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

// Parámetro GET: idespecialidad
$idespecialidad = isset($_GET["idespecialidad"]) ? intval($_GET["idespecialidad"]) : 0;

$sql = "SELECT d.iddoctor, d.nombres, d.apellidos,
               d.idespecialidad, e.nombre AS especialidad,
               d.telefono, d.correo, d.horario, d.consultorio
        FROM doctores d
        INNER JOIN especialidades e ON d.idespecialidad = e.idespecialidad
        WHERE d.idespecialidad = ?
        ORDER BY d.apellidos";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $idespecialidad);
$stmt->execute();
$result = $stmt->get_result();

$doctores = [];
while ($row = $result->fetch_assoc()) {
    $doctores[] = $row;
}

$stmt->close();
$conn->close();
echo json_encode($doctores, JSON_UNESCAPED_UNICODE);
?>
