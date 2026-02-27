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
    echo json_encode(["respuesta" => "error", "mensaje" => "Error de conexión"]);
    exit;
}

// Parámetros GET: usuario y password
$user = isset($_GET["usuario"])  ? trim($_GET["usuario"])  : "";
$pass = isset($_GET["password"]) ? trim($_GET["password"]) : "";

if (empty($user) || empty($pass)) {
    echo json_encode(["respuesta" => "incorrecto"]);
    $conn->close();
    exit;
}

$sql  = "SELECT idusuario, nombre FROM usuarios WHERE usuario = ? AND password = ? LIMIT 1";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ss", $user, $pass);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    echo json_encode([
        "respuesta" => "correcto",
        "usuario"   => $row["nombre"]
    ], JSON_UNESCAPED_UNICODE);
} else {
    echo json_encode(["respuesta" => "incorrecto"]);
}

$stmt->close();
$conn->close();
?>
