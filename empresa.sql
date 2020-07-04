-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-07-2020 a las 13:09:26
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empresa`
--
CREATE DATABASE IF NOT EXISTS `empresa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `empresa`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cliente` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `p_acumulado` int(11) NOT NULL,
  `saldo` float NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sexo` varchar(100) NOT NULL,
  `f_alta` varchar(100) NOT NULL,
  `f_baja` varchar(100) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `password`, `p_acumulado`, `saldo`, `nombre`, `apellido1`, `apellido2`, `dni`, `email`, `sexo`, `f_alta`, `f_baja`, `activo`) VALUES
('c2', 'p', 123, 123, 'c2', 'c2', 'c2', '1234123', '123123123', '12312312', '123123', 'qweqweq', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `cod_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cliente` varchar(20) NOT NULL,
  `cod_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`cod_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`cod_pedido`, `cod_cliente`, `cod_producto`, `cantidad`, `fecha`) VALUES
(26, 'c1', 2, 23, '0000-00-00'),
(30, 'c1', 2, 32, '0000-00-00'),
(33, 'c1', 2, 43, '2020-06-26'),
(34, 'c12', 2, 12, '2020-06-28'),
(35, 'c12', 2, 12, '2020-06-28'),
(36, 'c12', 12, 2, '2020-06-28'),
(37, 'c12', 32, 23, '2020-06-28'),
(38, 'c5', 3, 4, '0000-00-00'),
(39, 'c5', 3, 4, '0000-00-00'),
(40, 'c5', 3, 4, '0000-00-00'),
(41, 'c1', 4, 4, '0000-00-00'),
(42, 'c1', 4, 4, '0000-00-00'),
(43, 'c1', 4, 4, '0000-00-00'),
(44, 'c1', 54, 3, '2020-06-28'),
(45, 'c1', 2, 23, '2020-06-28'),
(46, 'c1', 2, 3, '2020-06-28'),
(47, 'c1', 3, 34444, '2020-06-29'),
(48, 'c1', 3, 23, '2020-06-29'),
(49, 'c1', 2, 45, '2020-06-29'),
(50, 'c1', 3, 44, '2020-06-29'),
(51, 'c1', 5, 123, '2020-06-29'),
(52, 'c1', 4, 23, '2020-06-29'),
(53, 'c1', 2, 45, '2020-06-30'),
(54, 'c2', 1, 1, '2020-06-30'),
(55, 'c2', 23, 23, '2020-06-30'),
(56, 'c1', 12, 12, '2020-07-01'),
(57, 'c1', 11, 11, '2020-07-01'),
(58, 'c1', 13, 13, '2020-07-01'),
(59, 'c1', 15, 15, '2020-07-01'),
(60, 'c1', 23, 23, '2020-07-01'),
(61, 'c1', 12, 12, '2020-07-02'),
(62, 'c1', 46, 46, '2020-07-02'),
(63, 'c1', 15, 15, '2020-07-02'),
(64, 'c3', 34, 34, '2020-07-02'),
(65, 'c1', 15, 15, '2020-07-02'),
(66, 'c3', 11, 11, '2020-07-02'),
(67, 'c3', 5, 5, '2020-07-02'),
(68, 'c1', 23, 23, '2020-07-02'),
(69, 'c1', 12, 12, '2020-07-03'),
(70, 'c1', 3, 32, '2020-07-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `cod_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`cod_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`cod_producto`, `nombre`, `descripcion`, `precio`) VALUES
(2, 'Bolígrafo', 'Utensilio para escribir que consiste en un tubo hueco de plástico o de metal con un depósito cilíndrico de una tinta viscosa en su interior y una bolita metálica en la punta que gira libremente y hace salir la tinta de forma uniforme.\r\n', 3.45),
(3, 'Cuaderno', 'Conjunto de hojas de papel, impresas o en blanco, unidas con una espiral o dobladas, encajadas o cosidas, que forman un libro delgado y que sirve para anotar cosas.', 7),
(4, 'Portátil', 'Máquina electrónica capaz de almacenar información y tratarla automáticamente mediante operaciones matemáticas y lógicas controladas por programas informáticos.\r\n', 765),
(5, 'Gafas', '(gafas) Objeto usado por algunas personas para corregir defectos de visión, o protegerse los ojos de reflejos dañinos, que consiste en dos cristales, con graduación óptica o sin ella, montados en un armazón o montura que se apoya en la nariz y que se suje', 270);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
