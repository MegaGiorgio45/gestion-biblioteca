CREATE DATABASE IF NOT EXISTS libreria;
USE libreria;

CREATE TABLE tienda (
    idTienda INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    ciudad VARCHAR(100) NOT NULL
);

CREATE TABLE editorial (
    idEditorial INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE libro (
    isbn CHAR(13) PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    anio_publicacion INT NOT NULL,
    paginas INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    editorial_idEditorial INT NOT NULL,
    FOREIGN KEY (editorial_idEditorial) REFERENCES editorial(idEditorial),
    CHECK (paginas > 0),
    CHECK (precio >= 0)
);

CREATE TABLE autor (
    idAutor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL,
    anio_nacimiento INT NOT NULL
);

CREATE TABLE libro_autor (
    libro_isbn CHAR(13) NOT NULL,
    autor_idAutor INT NOT NULL,
    rol VARCHAR(20) NOT NULL,
    PRIMARY KEY (libro_isbn, autor_idAutor),
    FOREIGN KEY (libro_isbn) REFERENCES libro(isbn),
    FOREIGN KEY (autor_idAutor) REFERENCES autor(idAutor),
    CHECK (rol IN ('principal', 'colaborador'))
);

CREATE TABLE empleado (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    cargo VARCHAR(20) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    tienda_idTienda INT NOT NULL,
    UNIQUE (dni, tienda_idTienda),
    FOREIGN KEY (tienda_idTienda) REFERENCES tienda(idTienda),
    CHECK (cargo IN ('librero', 'cajero', 'encargado'))
);
CREATE TABLE cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    es_socio BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_alta DATE
);

CREATE TABLE inventario (
    tienda_idTienda INT NOT NULL,
    libro_isbn CHAR(13) NOT NULL,
    stock INT NOT NULL,
    contado_el DATE NOT NULL,
    PRIMARY KEY (tienda_idTienda, libro_isbn),
    FOREIGN KEY (tienda_idTienda) REFERENCES tienda(idTienda),
    FOREIGN KEY (libro_isbn) REFERENCES libro(isbn),
    CHECK (stock >= 0)
);

CREATE TABLE pedido (
    idPedido INT PRIMARY KEY,
    tienda_idTienda INT NOT NULL,
    empleado_dni VARCHAR(9) NOT NULL,
    cliente_idCliente INT NOT NULL,
    fecha DATE NOT NULL,
    forma_pago VARCHAR(10) NOT NULL,
    estado VARCHAR(10) NOT NULL,
    FOREIGN KEY (tienda_idTienda) REFERENCES tienda(idTienda),
    FOREIGN KEY (empleado_dni, tienda_idTienda) REFERENCES empleado(dni, tienda_idTienda),
    FOREIGN KEY (cliente_idCliente) REFERENCES cliente(idCliente),
    CHECK (forma_pago IN ('efectivo', 'tarjeta', 'bizum')),
    CHECK (estado IN ('preparado', 'entregado', 'cancelado'))
);

CREATE TABLE detalle_pedido (
    pedido_idPedido INT NOT NULL,
    libro_isbn CHAR(13) NOT NULL,
    cantidad INT NOT NULL,
    precio_pagado DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (pedido_idPedido, libro_isbn),
    FOREIGN KEY (pedido_idPedido) REFERENCES pedido(idPedido),
    FOREIGN KEY (libro_isbn) REFERENCES libro(isbn),
    CHECK (cantidad > 0),
    CHECK (precio_pagado >= 0)
);

-- DATOS DE PRUEBA --

-- 1. Editoriales
INSERT INTO editorial (idEditorial, nombre, pais, telefono) VALUES
(1, 'Editorial1', 'España', '910111111'),
(2, 'Editorial2', 'España', '910222222'),
(3, 'Editorial3', 'México', '910333333');

-- 2. Tiendas
INSERT INTO tienda (idTienda, nombre, direccion, telefono, ciudad) VALUES
(1, 'Centro', 'Calle A 10', '976111111', 'Zaragoza'),
(2, 'Ribera', 'Calle B 5', '976222222', 'Zaragoza'),
(3, 'Universidad', 'Calle C 55', '976333333', 'Zaragoza');

-- 3. Autores
INSERT INTO autor (idAutor, nombre, nacionalidad, anio_nacimiento) VALUES
(1, 'Autor1', 'España', 1985),
(2, 'Autor2', 'España', 1978),
(3, 'Autor3', 'Colombia', 1990);

-- 4. Libros
INSERT INTO libro (isbn, titulo, anio_publicacion, paginas, precio, editorial_idEditorial) VALUES
('9788400000001', 'Libro1', 2021, 240, 18.50, 1),
('9788400000002', 'Libro2', 2022, 310, 24.90, 2),
('9788400000003', 'Libro3', 2020, 195, 16.00, 1),
('9788400000004', 'Libro4', 2023, 280, 21.50, 3),
('9788400000005', 'Libro5', 2024, 350, 27.00, 2);

-- 5. Relación entre libros y autores.
-- El libro 'Historias del rio' tiene dos autores.
INSERT INTO libro_autor (libro_isbn, autor_idAutor, rol) VALUES
('9788400000001', 1, 'principal'),
('9788400000002', 2, 'principal'),
('9788400000003', 3, 'principal'),
('9788400000004', 1, 'principal'),
('9788400000004', 2, 'colaborador'),
('9788400000005', 2, 'principal');

-- 6. Empleados: uno por tienda para poder registrar pedidos en las tres.
INSERT INTO empleado (dni, nombre, apellidos, cargo, fecha_contratacion, email, tienda_idTienda) VALUES
('12345678A', 'Empleado1', 'Empleado1', 'encargado', '2022-02-01', 'e1@villaserena.es', 1),
('23456789B', 'Empleado2', 'Empleado2', 'cajero', '2023-05-15', 'e2@villaserena.es', 2),
('34567890C', 'Empleado3', 'Empleado3', 'librero', '2021-09-10', 'e3@villaserena.es', 3);

-- 7. Clientes: dos socios y un cliente que no es socio.
INSERT INTO cliente (idCliente, nombre, email, telefono, es_socio, fecha_alta) VALUES
(1, 'Cliente1', 'c1@email.com', '600111111', TRUE, '2024-01-12'),
(2, 'Cliente2', 'c2@email.com', '600222222', TRUE, '2024-03-20'),
(3, 'Cliente3', 'c3@email.com', NULL, FALSE, NULL);

-- 8. Inventario de los cinco libros en las tres tiendas.
INSERT INTO inventario (tienda_idTienda, libro_isbn, stock, contado_el) VALUES
(1, '9788400000001', 5, '2026-09-01'),
(1, '9788400000002', 0, '2026-09-01'),
(1, '9788400000003', 3, '2026-09-01'),
(1, '9788400000004', 2, '2026-09-01'),
(1, '9788400000005', 4, '2026-09-01'),
(2, '9788400000001', 2, '2026-09-02'),
(2, '9788400000002', 6, '2026-09-02'),
(2, '9788400000003', 0, '2026-09-02'),
(2, '9788400000004', 3, '2026-09-02'),
(2, '9788400000005', 1, '2026-09-02'),
(3, '9788400000001', 0, '2026-09-03'),
(3, '9788400000002', 3, '2026-09-03'),
(3, '9788400000003', 2, '2026-09-03'),
(3, '9788400000004', 0, '2026-09-03'),
(3, '9788400000005', 5, '2026-09-03');

-- 9. Pedidos
INSERT INTO pedido (idPedido, tienda_idTienda, empleado_dni, cliente_idCliente, fecha, forma_pago, estado) VALUES
(1, 1, '12345678A', 1, '2026-09-05', 'tarjeta', 'entregado'),
(2, 1, '12345678A', 2, '2026-09-12', 'efectivo', 'entregado'),
(3, 2, '23456789B', 1, '2026-09-18', 'bizum', 'entregado'),
(4, 2, '23456789B', 3, '2026-09-25', 'tarjeta', 'preparado'),
(5, 3, '34567890C', 2, '2026-10-02', 'efectivo', 'entregado');

-- 10. Líneas de pedido: cada pedido contiene varios libros.
INSERT INTO detalle_pedido (pedido_idPedido, libro_isbn, cantidad, precio_pagado) VALUES
(1, '9788400000001', 1, 18.50),
(1, '9788400000002', 1, 24.90),
(2, '9788400000003', 2, 15.00),
(2, '9788400000005', 1, 27.00),
(3, '9788400000001', 1, 18.50),
(3, '9788400000004', 2, 21.50),
(4, '9788400000002', 1, 24.90),
(4, '9788400000003', 1, 16.00),
(5, '9788400000004', 1, 21.50),
(5, '9788400000005', 2, 26.00);
