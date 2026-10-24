-- Inicialización de la base de datos de la aplicación de biblioteca.
-- ATENCIÓN: elimina y vuelve a crear libros; los datos anteriores se perderán.
CREATE DATABASE IF NOT EXISTS biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE biblioteca;
DROP TABLE IF EXISTS libros;
CREATE TABLE libros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    CHECK (precio >= 0),
    CHECK (stock >= 0)
);
INSERT INTO libros (id, titulo, autor, precio, stock) VALUES
(1, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 19.95, 8),
(2, 'La sombra del viento', 'Carlos Ruiz Zafón', 17.50, 5),
(3, 'Cien años de soledad', 'Gabriel García Márquez', 21.00, 4),
(4, 'La casa de los espíritus', 'Isabel Allende', 18.75, 6),
(5, 'El principito', 'Antoine de Saint-Exupéry', 9.95, 12),
(6, 'Rebelión en la granja', 'George Orwell', 12.50, 7),
(7, 'La ciudad y los perros', 'Mario Vargas Llosa', 16.90, 3),
(8, 'Frankenstein', 'Mary Shelley', 14.25, 9);
