/*
 ************************************************************
 *	Empresa			:	WilsonStore							*
 *	Software		:	Sistema de Registro de Catálogo		*
 *	DBMS			:	Oracle								*
 *	Base de Datos 	:	wilsonstore							*
 *	Script			:	Carga Datos							*
 *	Responsable		:	Christian Joel Benites Sánchez 		*
 *	Telefono		:	(511) 992457747						*
 *	Email			:	cbenitesanchez@gmail.com			*
 ************************************************************
*/


-- =============================================
-- Cargar Datos de Prueba
-- =============================================

CREATE OR REPLACE FUNCTION fn_pventa( p_costo NUMBER, pct_ganan NUMBER )
RETURN	NUMBER
AS		v_pventa NUMBER(10,2);
BEGIN	v_pventa := p_costo + pct_ganan * p_costo;
		RETURN v_pventa;
END;
/

CREATE OR REPLACE FUNCTION fn_poferta( p_costo NUMBER, pct_ganan NUMBER )
RETURN	NUMBER
AS		v_poferta NUMBER(10,2);
BEGIN	v_poferta := p_costo + 0.5 * pct_ganan * p_costo;
		RETURN v_poferta;
END;
/

CREATE OR REPLACE FUNCTION fn_impuesto( p_subtotal NUMBER )
RETURN	NUMBER
AS		v_impuesto NUMBER(10,2);
BEGIN	v_impuesto := 0.18 * p_subtotal;
		RETURN v_impuesto;
END;
/

CREATE OR REPLACE FUNCTION fn_total( p_subtotal NUMBER, p_impuesto NUMBER )
RETURN	NUMBER
AS		v_total NUMBER(10,2);
BEGIN	v_total := p_subtotal + p_impuesto;
		RETURN v_total;
END;
/

-- Tabla: Empleado
INSERT INTO Empleado VALUES ( '0001', 'CACERES', 'VENTO', 'JORGE', 'LIMA', 'AV. Cesar Vallejo 515 - Comas' );
INSERT INTO Empleado VALUES ( '0002', 'SUCRE', 'PINTADO', 'CARLOS', 'HUANCAYO', 'Jr. Los Mirones 212 - Pasco' );
INSERT INTO Empleado VALUES ( '0003', 'MALPARTIDA', 'RAMOS', 'ELIZABETH', 'AREQUIPA', 'Av. Filomeno 566 - Villa' );
INSERT INTO Empleado VALUES ( '0004', 'BENITES', 'SANCHEZ', 'CHRISTIAN', 'LIMA', 'Av. Armando Filomeno 140 - Rimac' );
INSERT INTO Empleado VALUES ( '0005', 'VILCA', 'MACO', 'ANDRES', 'LIMA', 'Jr. Jupiter 15 - Comas' );
INSERT INTO Empleado VALUES ( '0006', 'JIMENEZ', 'SUCRE', 'JORGE', 'LIMA', 'Av. Buenavista 25 - La Molina' );


-- Tabla: Usuario
INSERT INTO Usuario VALUES ( '0004', 'christian', 'benites' );
INSERT INTO Usuario VALUES ( '0005', 'andres', 'vilca' );
INSERT INTO Usuario VALUES ( '0006', 'jorge', 'sucre' );


-- Tabla: Cliente
INSERT INTO Cliente VALUES ( '000001', 'BELAUNDE', 'CASTRO', 'CAROLINA', 'AREQUIPA', 'AV. FILOMENO 12 - RIMAC' );
INSERT INTO Cliente VALUES ( '000002', 'SANCHEZ', 'SOTO', 'PEDRO', 'LIMA', 'AV. EL SOL 34 - OLIVOS' );
INSERT INTO Cliente VALUES ( '000003', 'ZAPATA', 'JIMENEZ', 'LUIS', 'LIMA', 'JR. QUILCA 12 - COMAS' );
INSERT INTO Cliente VALUES ( '000004', 'MORALES', 'RAMOS', 'JAIME', 'TACNA', 'CALLE 75 MZ. 3 - CARABAYLLO' );
INSERT INTO Cliente VALUES ( '000005', 'GUTIERREZ', 'FLORES', 'XIMENA', 'LIMA', 'AV. TACNA 44 - CERCADO' );


-- Tabla: Categoria
INSERT INTO Categoria VALUES( '0001', 'LINEA BLANCA' );
INSERT INTO Categoria VALUES( '0002', 'ELECTRODOMESTICOS' );
INSERT INTO Categoria VALUES( '0003', 'TV Y VIDEO' );
INSERT INTO Categoria VALUES( '0004', 'AUDIO Y MP3' );
INSERT INTO Categoria VALUES( '0005', 'COMPUTACION' );


-- Tabla: Articulo
INSERT INTO Articulo VALUES( '000001', 'Refrigeradora Coldex CoolStyle 290N', 'Funcion AirFlowStop. Sistema No Frost. Gas Ecologico.',
							 60, 1149, 0.60, fn_pventa(1149,0.60), fn_poferta(1149,0.60), '0001' );
INSERT INTO Articulo VALUES( '000002', 'Refrigeradora Coldex CoolStyle 331N', 'Funcion AirFlowStop/Sistema No Frost/Compartimento Frio Extremo',
							 40, 1359, 0.65, fn_pventa(1359,0.65), fn_poferta(1359,0.65), '0001' );
INSERT INTO Articulo VALUES( '000003', 'Cocina a gas Bosch Pro 7200', 'Encendido electrico en hornillas y horno/Quemador Pro',
							 30, 1899, 0.63, fn_pventa(1899,0.63), fn_poferta(1899,0.63), '0001' );
INSERT INTO Articulo VALUES( '000004', 'Lavadora Automatica LG de 10.5 Kg', 'Silver Tambor Giratorio/TurboDrum/Lavado en 3 pasos',
							 35, 1099, 0.70, fn_pventa(1099,0.70), fn_poferta(1099,0.70), '0001' );
INSERT INTO Articulo VALUES( '000005', 'Cocina Dulce Electrolux EKGA24', '4 quemadores sellados/Encendido electrico/Grill electrico',
							 43, 899, 0.64, fn_pventa(899,0.64), fn_poferta(899,0.64), '0001' );
INSERT INTO Articulo VALUES( '000006', 'Lavadora Automatica Electrolux de 14 Kg', 'Con 7 programar de lavado/Sistema especial Fuzzy',
							 33, 999, 0.72, fn_pventa(999,0.72), fn_poferta(999,0.72), '0001' );
INSERT INTO Articulo VALUES( '000007', 'TV LED AOC LE50H254', '50 pulgadas/Conexion a PC/AV/Entrada HDMIx3/Entrada USBx1',
							 30, 2599, 0.60, fn_pventa(2599,0.60), fn_poferta(2599,0.60), '0003' );
INSERT INTO Articulo VALUES( '000008', 'TV Plasma LG 50PN4500', '50 pulgadas/HD resolucion 1024x768/Entrada HDMIx2/Entrada USBx1',
							 32, 1799, 0.60, fn_pventa(1799,0.60), fn_poferta(1799,0.60), '0003' );
INSERT INTO Articulo VALUES( '000009', 'Reproductor DVD Philips', 'ProReaderDrive/Reproduce MP3, WMA y fotos digitales JPEG/Reproduce CD',
							 25, 129, 0.68, fn_pventa(129,0.68), fn_poferta(129,0.68), '0003' );
INSERT INTO Articulo VALUES( '000010', 'TV LED Sony KDL', '32 pulgadas/HD Resolucion 1360x720/HD Ready/Entrada HDMIx2',
							 30, 1799, 0.72, fn_pventa(1799,0.72), fn_poferta(1799,0.72), '0003' );
INSERT INTO Articulo VALUES( '000011', 'SmartTV Samsung Serie F6400', 'LED 3D SmartTV/Full HD/Procesador Dual Core/Smart Hub 2013',
							 35, 3229, 0.66, fn_pventa(3229,0.66), fn_poferta(3229,0.66), '0003' );
INSERT INTO Articulo VALUES( '000012', 'Licuadora Avance Philips HR 2097', '800 Watts/Acero inoxidable/2 Lt/Cuchillas Pro blend 6',
							 40, 429, 0.66, fn_pventa(429,0.66), fn_poferta(429,0.66), '0002' );
INSERT INTO Articulo VALUES( '000013', 'Plancha Philips GC2960', '2200 Watts/Vapor vertical y horizontal/Base Steam Glide',
							 50, 89, 0.72, fn_pventa(89,0.72), fn_poferta(89,0.72), '0002' );
INSERT INTO Articulo VALUES( '000014', 'Licuadora Oster 6826', '450 Watts/Vaso de vidrio refractario/12 velocidades/Cuchilla pica hielo/1.25 Lts',
							 38, 239, 0.74, fn_pventa(239,0.74), fn_poferta(239,0.74), '0002' );
INSERT INTO Articulo VALUES( '000015', 'Hervidor Imaco KE-2003', 'Cap. 1.7 Lts/Apagado automatico/Resistencia cubierta/Indicador de nivel de agua',
							 40, 69.9, 0.62, fn_pventa(69.9,0.62), fn_poferta(69.9,0.62), '0002' );
INSERT INTO Articulo VALUES( '000016', 'Cafetera Imaco CM-1595', 'Cap. 12 Tazas/Sistema Antigoteo/Luz indicadora/Foltro removible lavable',
							 30, 99.9, 0.70, fn_pventa(99.9,0.70), fn_poferta(99.9,0.70), '0002' );
INSERT INTO Articulo VALUES( '000017', 'Cafetera Electrolux CMC10', 'Capacidad 12 Tazas/Canasta para el filtro removible/Valvula antigoteo',
							 37, 119, 0.72, fn_pventa(119,0.72), fn_poferta(119.9,0.72), '0002' );
INSERT INTO Articulo VALUES( '000018', 'Cafetera Oster XMC120', 'Capacidad 10 Tazas/Canasta para el filtro removible/Valvula antigoteo',
							 30, 109, 0.70, fn_pventa(109,0.70), fn_poferta(109.9,0.70), '0002' );
INSERT INTO Articulo VALUES( '000019', 'Licuadora Oster 4655', '600 Watts/3 velocidades con base metálica cromada/Vaso de vidrio refractario',
							 40, 319, 0.63, fn_pventa(319,0.63), fn_poferta(319.9,0.63), '0002' );
INSERT INTO Articulo VALUES( '000020', 'Cafetera Philips HD7450', '4 Tazas/Porta Filtro Desmontable/Sistema antigoteo/Enrollacable',
							 31, 119, 0.61, fn_pventa(119,0.61), fn_poferta(119.9,0.61), '0002' );
INSERT INTO Articulo VALUES( '000021', 'Olla Arrocera Oster 006030', '2.2 Lts. de capacidad/Incluye medidora y cucharon',
							 45, 169, 0.68, fn_pventa(169,0.68), fn_poferta(169.9,0.68), '0002' );
INSERT INTO Articulo VALUES( '000022', 'Laptop Asus N56VZ', 'Procesador Intel Core i7/RAM 8 GB DDR3 1600 MHZ/HDD 1TB/NVIDIA GeForce 4GB',
							 30, 3599, 0.63, fn_pventa(3599,0.63), fn_poferta(3599,0.63), '0005' );
INSERT INTO Articulo VALUES( '000023', 'Laptop Asus TAICHI 21', 'Intel Core i5/2 Pantallas de 11.6 FHD/4 GB DDR3 1600 MHZ/SSD 256GB.',
							 25, 5299, 0.62, fn_pventa(5299,0.62), fn_poferta(5299,0.62), '0005' );
INSERT INTO Articulo VALUES( '000024', 'Tablet Asus VivoTab RT', 'Procesador NVIDIA Tegra 3 Quad Core/Pantalla LED 10.1 Retroiluminada/RAM 1GB',
							 30, 1699, 0.65, fn_pventa(1699,0.65), fn_poferta(1699,0.65), '0005' );
INSERT INTO Articulo VALUES( '000025', 'Equipo Sonido MX-F850 Samsung', '1200 Watts./2.1 Canales/MP3 WMA/Mi Karaoke/2 USB REC/17 Modos EQ',
							 20, 1199, 0.62, fn_pventa(1199,0.62), fn_poferta(1199,0.62), '0004' );
INSERT INTO Articulo VALUES( '000026', 'Reproductor BD-DVD Samsung', '500 Watts/5.1 Canales/3D BD Home Theater/Netflix-Youtube',
							 33, 799, 0.65, fn_pventa(799,0.65), fn_poferta(799,0.65), '0004' );
INSERT INTO Articulo VALUES( '000027', 'Equipo Sonido Shake-7 Sony', '2850 Watts RMS/Parlantes 4 Vias/Bluetooth/Funcion One Touch',
							 15, 2799, 0.70, fn_pventa(2799,0.70), fn_poferta(2799,0.70), '0004' );
INSERT INTO Articulo VALUES( '000028', 'Home Theater HTB5570D Philips', 'Home 5.1/Full HD 3D Blu-Ray/800 Watts/Doble conexion/HDMI Karaoke',
							 40, 1299, 0.75, fn_pventa(1299,0.75), fn_poferta(1299,0.75), '0004' );
INSERT INTO Articulo VALUES( '000029', 'Audio Home System MCMI 150 Philips', '70 Watts RMS/Reproduce MP3,CD,CD-R,CD-RW/USB',
							 45, 299, 0.69, fn_pventa(299,0.69), fn_poferta(299,0.69), '0004' );
INSERT INTO Articulo VALUES( '000030', 'Reproductor Multimedia MRD-208 MIRAY', 'Pantalla TFT 18 pulgadas/Ecualizador SRS-WOW',
							 25, 129, 0.60, fn_pventa(129,0.60), fn_poferta(129,0.60), '0004' );
INSERT INTO Articulo VALUES( '000031', 'Laptop Compaq HP CQ45-910', 'Intel B830 1.8 GHZ/RAM DDR3 4GB/HDD 500GB/DVD-RW/14 pulgadas/Wi-Fi',
							 40, 1299, 0.75, fn_pventa(1299,0.75), fn_poferta(1299,0.75), '0005' );
INSERT INTO Articulo VALUES( '000032', 'Laptop Acer E1-531-4650', 'Intel PDC B960 2.2 GHZ/15.6 pulgadas/HDD 500GB/RAM DDR 4GB/Wi-Fi',
							 30, 1200, 0.70, fn_pventa(1200,0.70), fn_poferta(1200,0.70), '0005' );
INSERT INTO Articulo VALUES( '000033', 'Laptop HP Pavilion 14-B180', 'Core i3-3227U/RAM SDDR3 4GB/HDD 500GB/14 pulgadas/Windows 8',
							 25, 2090, 0.62, fn_pventa(2090,0.62), fn_poferta(2090,0.62), '0005' );
INSERT INTO Articulo VALUES( '000034', 'Laptop Toshiba U845W-SP430', 'Procesador Core i5 1.7GHZ/RAM DDR 6GB/HDD 500GB/14 pulgadas',
							 33, 2699, 0.60, fn_pventa(2699,0.60), fn_poferta(2699,0.60), '0005' );
INSERT INTO Articulo VALUES( '000035', 'Laptop Sony SVF1421CL', 'Intel Core i3 1.90GHZ/HDD 750GB/RAM DDR3 6GB/Video Intel Graphics 4000/Windows 8',
							 20, 1999, 0.80, fn_pventa(1999,0.80), fn_poferta(1999,0.80), '0005' );
INSERT INTO Articulo VALUES( '000036', 'Impresora Canon PIXMA MX391', 'Multifuncional ADF integrado/8.7 Negro, 5.0 Color/Scanner 1200x2400dpi',
							 40, 269, 0.60, fn_pventa(269,0.60), fn_poferta(269,0.60), '0005' );
INSERT INTO Articulo VALUES( '000037', 'Impresora EPSON WorkForce M205', 'Imprime.Copia.Escanea/35 PPM/1440x720 dpi/Imprime inalambricamente',
							 45, 729, 0.65, fn_pventa(729,0.65), fn_poferta(729,0.65), '0005' );
INSERT INTO Articulo VALUES( '000038', 'Impresora EPSON Expression XP', 'Imprime.Copia.Escanea.Wi-Fi/Cartuchos individuales',
							 42, 249, 0.62, fn_pventa(249,0.62), fn_poferta(249,0.62), '0005' );

							 
-- Tabla: Venta
INSERT INTO Venta VALUES( '000001', '10/10/2010', 2159.10, fn_impuesto(2159.10), fn_total(2159.10,fn_impuesto(2159.10)), '0004', '000002' );
INSERT INTO Venta VALUES( '000002', '15/10/2010', 4318.40, fn_impuesto(4318.40), fn_total(4318.40,fn_impuesto(4318.40)), '0005', '000004' );
INSERT INTO Venta VALUES( '000003', '22/11/2010', 1526.50, fn_impuesto(1526.50), fn_total(1526.50,fn_impuesto(1526.50)), '0004', '000001' );
INSERT INTO Venta VALUES( '000004', '02/12/2010', 4678.95, fn_impuesto(4678.95), fn_total(4678.95,fn_impuesto(4678.95)), '0006', '000002' );
INSERT INTO Venta VALUES( '000005', '17/01/2011', 4111.65, fn_impuesto(4111.65), fn_total(4111.65,fn_impuesto(4111.65)), '0006', '000003' );
INSERT INTO Venta VALUES( '000006', '05/02/2011', 3602.00, fn_impuesto(4111.65), fn_total(4111.65,fn_impuesto(4111.65)), '0006', '000003' );
INSERT INTO Venta VALUES( '000007', '20/02/2011', 8670.37, fn_impuesto(8670.37), fn_total(8670.37,fn_impuesto(8670.37)), '0004', '000003' );
INSERT INTO Venta VALUES( '000008', '09/03/2011', 5360.14, fn_impuesto(5360.14), fn_total(5360.14,fn_impuesto(5360.14)), '0004', '000003' );
INSERT INTO Venta VALUES( '000009', '19/03/2011', 1318.35, fn_impuesto(1318.35), fn_total(1318.35,fn_impuesto(1318.35)), '0004', '000003' );
INSERT INTO Venta VALUES( '000010', '29/03/2011', 6700.68, fn_impuesto(6700.68), fn_total(6700.68,fn_impuesto(6700.68)), '0004', '000003' );
INSERT INTO Venta VALUES( '000011', '14/04/2011', 519.97, fn_impuesto(519.97), fn_total(519.97,fn_impuesto(519.97)), '0005', '000003' );
INSERT INTO Venta VALUES( '000012', '30/04/2011', 753.78, fn_impuesto(753.78), fn_total(753.78,fn_impuesto(753.78)), '0005', '000003' );
INSERT INTO Venta VALUES( '000013', '06/05/2011', 5866.37, fn_impuesto(5866.37), fn_total(5866.37,fn_impuesto(5866.37)), '0004', '000003' );
INSERT INTO Venta VALUES( '000014', '26/05/2011', 2803.35, fn_impuesto(2803.35), fn_total(2803.35,fn_impuesto(2803.35)), '0004', '000003' );
INSERT INTO Venta VALUES( '000015', '01/06/2011', 415.86, fn_impuesto(415.86), fn_total(415.86,fn_impuesto(415.86)), '0004', '000003' );
INSERT INTO Venta VALUES( '000016', '11/06/2011', 1318.35, fn_impuesto(1318.35), fn_total(1318.35,fn_impuesto(1318.35)), '0005', '000003' );
INSERT INTO Venta VALUES( '000017', '29/06/2011', 339.72, fn_impuesto(339.72), fn_total(339.72,fn_impuesto(339.72)), '0004', '000003' );
INSERT INTO Venta VALUES( '000018', '08/07/2011', 6727.17, fn_impuesto(6727.17), fn_total(6727.17,fn_impuesto(6727.17)), '0004', '000003' );
INSERT INTO Venta VALUES( '000019', '15/07/2011', 916.82, fn_impuesto(916.82), fn_total(916.82,fn_impuesto(916.82)), '0004', '000003' );
INSERT INTO Venta VALUES( '000020', '30/07/2011', 1318.35, fn_impuesto(1318.35), fn_total(1318.35,fn_impuesto(1318.35)), '0005', '000003' );
INSERT INTO Venta VALUES( '000021', '03/08/2011', 3247.36, fn_impuesto(3247.36), fn_total(3247.36,fn_impuesto(3247.36)), '0004', '000003' );
INSERT INTO Venta VALUES( '000022', '23/08/2011', 2878.40, fn_impuesto(2878.40), fn_total(2878.40,fn_impuesto(2878.40)), '0004', '000003' );
INSERT INTO Venta VALUES( '000023', '12/09/2011', 3598.20, fn_impuesto(3598.20), fn_total(3598.20,fn_impuesto(3598.20)), '0005', '000003' );
INSERT INTO Venta VALUES( '000024', '22/09/2011', 1610.52, fn_impuesto(1610.52), fn_total(1610.52,fn_impuesto(1610.52)), '0004', '000003' );
INSERT INTO Venta VALUES( '000025', '02/10/2011', 2470.40, fn_impuesto(2470.40), fn_total(2470.40,fn_impuesto(2470.40)), '0004', '000003' );
INSERT INTO Venta VALUES( '000026', '14/10/2011', 568.48, fn_impuesto(568.48), fn_total(568.48,fn_impuesto(568.48)), '0004', '000003' );
INSERT INTO Venta VALUES( '000027', '30/10/2011', 2803.35, fn_impuesto(2803.35), fn_total(2803.35,fn_impuesto(2803.35)), '0004', '000003' );
INSERT INTO Venta VALUES( '000028', '08/11/2011', 3094.28, fn_impuesto(3094.28), fn_total(3094.28,fn_impuesto(3094.28)), '0004', '000003' );
INSERT INTO Venta VALUES( '000029', '13/11/2011', 928.86, fn_impuesto(928.86), fn_total(928.86,fn_impuesto(928.86)), '0004', '000003' );
INSERT INTO Venta VALUES( '000030', '12/12/2011', 487.75, fn_impuesto(487.75), fn_total(487.75,fn_impuesto(487.75)), '0004', '000003' );
INSERT INTO Venta VALUES( '000031', '20/12/2011', 2273.25, fn_impuesto(2273.25), fn_total(2273.25,fn_impuesto(2273.25)), '0004', '000003' );
INSERT INTO Venta VALUES( '000032', '24/12/2011', 5360.14, fn_impuesto(5360.14), fn_total(5360.14,fn_impuesto(5360.14)), '0006', '000004' );
INSERT INTO Venta VALUES( '000033', '24/12/2011', 2273.25, fn_impuesto(2273.25), fn_total(2273.25,fn_impuesto(2273.25)), '0005', '000005' );
INSERT INTO Venta VALUES( '000034', '04/01/2012', 7176.12, fn_impuesto(7176.12), fn_total(7176.12,fn_impuesto(7176.12)), '0006', '000003' );
INSERT INTO Venta VALUES( '000035', '24/01/2012', 2412.99, fn_impuesto(2412.99), fn_total(2412.99,fn_impuesto(2412.99)), '0004', '000003' );
INSERT INTO Venta VALUES( '000036', '10/02/2012', 191.59, fn_impuesto(191.59), fn_total(191.59,fn_impuesto(191.59)), '0004', '000003' );
INSERT INTO Venta VALUES( '000037', '20/02/2012', 113.24, fn_impuesto(113.24), fn_total(113.24,fn_impuesto(113.24)), '0004', '000002' );
INSERT INTO Venta VALUES( '000038', '02/03/2012', 415.86, fn_impuesto(415.86), fn_total(415.86,fn_impuesto(415.86)), '0004', '000003' );
INSERT INTO Venta VALUES( '000039', '29/03/2012', 8454.42, fn_impuesto(8454.42), fn_total(8454.42,fn_impuesto(8454.42)), '0004', '000002' );
INSERT INTO Venta VALUES( '000040', '09/04/2012', 1219.56, fn_impuesto(1219.56), fn_total(1219.56,fn_impuesto(1219.56)), '0005', '000003' );
INSERT INTO Venta VALUES( '000041', '15/04/2012', 2878.40, fn_impuesto(2878.40), fn_total(2878.40,fn_impuesto(2878.40)), '0005', '000002' );
INSERT INTO Venta VALUES( '000042', '26/04/2012', 2273.25, fn_impuesto(2273.25), fn_total(2273.25,fn_impuesto(2273.25)), '0005', '000003' );
INSERT INTO Venta VALUES( '000043', '24/12/2012', 8584.38, fn_impuesto(8584.38), fn_total(8584.38,fn_impuesto(8584.38)), '0005', '000004' );
INSERT INTO Venta VALUES( '000044', '05/01/2013', 202.31, fn_impuesto(202.31), fn_total(202.31,fn_impuesto(202.31)), '0004', '000004' );
INSERT INTO Venta VALUES( '000045', '19/01/2013', 5756.80, fn_impuesto(5756.80), fn_total(5756.80,fn_impuesto(5756.80)), '0004', '000004' );
INSERT INTO Venta VALUES( '000046', '29/01/2013', 4158.40, fn_impuesto(4158.40), fn_total(4158.40,fn_impuesto(4158.40)), '0004', '000004' );
INSERT INTO Venta VALUES( '000047', '08/02/2013', 918.85, fn_impuesto(918.85), fn_total(918.85,fn_impuesto(918.85)), '0004', '000001' );
INSERT INTO Venta VALUES( '000048', '28/02/2013', 283.92, fn_impuesto(283.92), fn_total(283.92,fn_impuesto(283.92)), '0004', '000001' );
INSERT INTO Venta VALUES( '000049', '06/03/2013', 403.38, fn_impuesto(403.38), fn_total(403.38,fn_impuesto(403.38)), '0004', '000004' );
INSERT INTO Venta VALUES( '000050', '27/03/2013', 4318.40, fn_impuesto(4318.40), fn_total(4318.40,fn_impuesto(4318.40)), '0004', '000004' );
INSERT INTO Venta VALUES( '000051', '08/04/2013', 6405.28, fn_impuesto(6405.28), fn_total(6405.28,fn_impuesto(6405.28)), '0004', '000004' );
INSERT INTO Venta VALUES( '000052', '12/04/2013', 191.59, fn_impuesto(191.59), fn_total(191.59,fn_impuesto(191.59)), '0004', '000001' );
INSERT INTO Venta VALUES( '000053', '28/04/2013', 169.83, fn_impuesto(169.83), fn_total(169.83,fn_impuesto(169.83)), '0004', '000001' );
INSERT INTO Venta VALUES( '000054', '11/05/2013', 5425.80, fn_impuesto(5425.80), fn_total(5425.80,fn_impuesto(5425.80)), '0004', '000004' );
INSERT INTO Venta VALUES( '000055', '23/05/2013', 938.44, fn_impuesto(938.44), fn_total(938.44,fn_impuesto(938.44)), '0004', '000001' );
INSERT INTO Venta VALUES( '000056', '05/06/2013', 712.14, fn_impuesto(712.14), fn_total(712.14,fn_impuesto(712.14)), '0004', '000004' );
INSERT INTO Venta VALUES( '000057', '10/06/2013', 1868.30, fn_impuesto(1868.30), fn_total(1868.30,fn_impuesto(1868.30)), '0004', '000004' );
INSERT INTO Venta VALUES( '000058', '28/06/2013', 113.24, fn_impuesto(113.24), fn_total(113.24,fn_impuesto(113.24)), '0004', '000001' );
INSERT INTO Venta VALUES( '000059', '02/07/2013', 679.38, fn_impuesto(679.38), fn_total(679.38,fn_impuesto(679.38)), '0004', '000004' );
INSERT INTO Venta VALUES( '000060', '15/07/2013', 519.97, fn_impuesto(519.97), fn_total(519.97,fn_impuesto(519.97)), '0004', '000004' );
INSERT INTO Venta VALUES( '000061', '30/07/2013', 2479.65, fn_impuesto(2479.65), fn_total(2479.65,fn_impuesto(2479.65)), '0004', '000001' );
INSERT INTO Venta VALUES( '000062', '01/08/2013', 1474.36, fn_impuesto(1474.36), fn_total(1474.36,fn_impuesto(1474.36)), '0004', '000004' );
INSERT INTO Venta VALUES( '000063', '16/08/2013', 1423.42, fn_impuesto(3385.80), fn_total(3385.80,fn_impuesto(3385.80)), '0004', '000002' );
INSERT INTO Venta VALUES( '000064', '06/09/2013', 3385.80, fn_impuesto(3385.80), fn_total(3385.80,fn_impuesto(3385.80)), '0004', '000004' );
INSERT INTO Venta VALUES( '000065', '19/09/2013', 2242.35, fn_impuesto(2242.35), fn_total(2242.35,fn_impuesto(2242.35)), '0004', '000002' );
INSERT INTO Venta VALUES( '000066', '28/09/2013', 216.72, fn_impuesto(216.72), fn_total(216.72,fn_impuesto(216.72)), '0005', '000003' );
INSERT INTO Venta VALUES( '000067', '09/10/2013', 459.24, fn_impuesto(459.24), fn_total(459.24,fn_impuesto(459.24)), '0004', '000001' );
INSERT INTO Venta VALUES( '000068', '25/10/2013', 3260.73, fn_impuesto(3260.73), fn_total(3260.73,fn_impuesto(3260.73)), '0004', '000005' );

							 
-- Tabla: Detalle
INSERT INTO Detalle VALUES( '000001', '000025', 1, 1942.38 );
INSERT INTO Detalle VALUES( '000001', '000009', 1, 216.72 );
INSERT INTO Detalle VALUES( '000002', '000034', 1, 4318.40 );
INSERT INTO Detalle VALUES( '000003', '000018', 2, 370.60 );
INSERT INTO Detalle VALUES( '000003', '000015', 3, 339.72 );
INSERT INTO Detalle VALUES( '000003', '000030', 2, 412.80);
INSERT INTO Detalle VALUES( '000003', '000038', 1, 403.38 );
INSERT INTO Detalle VALUES( '000004', '000037', 2, 2405.70 );
INSERT INTO Detalle VALUES( '000004', '000031', 1, 2273.25 );
INSERT INTO Detalle VALUES( '000005', '000031', 1, 2273.25 );
INSERT INTO Detalle VALUES( '000005', '000001', 1, 1838.40 );
INSERT INTO Detalle VALUES( '000006', '000006', 1, 1718.28 );
INSERT INTO Detalle VALUES( '000006', '000005', 1, 1474.36 );
INSERT INTO Detalle VALUES( '000006', '000017', 2, 409.36 );
INSERT INTO Detalle VALUES( '000007', '000004', 2, 3736.60 );
INSERT INTO Detalle VALUES( '000007', '000003', 1, 3095.37 );
INSERT INTO Detalle VALUES( '000007', '000001', 1, 1838.40 );
INSERT INTO Detalle VALUES( '000008', '000011', 1, 5360.14 );
INSERT INTO Detalle VALUES( '000009', '000026', 1, 1318.35 );
INSERT INTO Detalle VALUES( '000010', '000027', 1, 4758.30 );
INSERT INTO Detalle VALUES( '000010', '000025', 1, 1942.38 );
INSERT INTO Detalle VALUES( '000011', '000019', 1, 519.97 );
INSERT INTO Detalle VALUES( '000012', '000018', 2, 370.60 );
INSERT INTO Detalle VALUES( '000012', '000020', 2, 383.18 );
INSERT INTO Detalle VALUES( '000013', '000022', 1, 5866.37 );
INSERT INTO Detalle VALUES( '000014', '000024', 1, 2803.35 );
INSERT INTO Detalle VALUES( '000015', '000014', 1, 415.86 );
INSERT INTO Detalle VALUES( '000016', '000026', 1, 1318.35 );
INSERT INTO Detalle VALUES( '000017', '000015', 3, 339.72 );
INSERT INTO Detalle VALUES( '000018', '000036', 2, 860.80 );
INSERT INTO Detalle VALUES( '000018', '000022', 1, 5866.37 );
INSERT INTO Detalle VALUES( '000019', '000017', 1, 204.68 );
INSERT INTO Detalle VALUES( '000019', '000012', 1, 712.14 );
INSERT INTO Detalle VALUES( '000020', '000026', 1, 1318.35 );
INSERT INTO Detalle VALUES( '000021', '000010', 1, 3094.28 );
INSERT INTO Detalle VALUES( '000021', '000013', 1, 153.08 );
INSERT INTO Detalle VALUES( '000022', '000008', 1, 2878.40 );
INSERT INTO Detalle VALUES( '000023', '000035', 1, 3598.20 );
INSERT INTO Detalle VALUES( '000024', '000029', 2, 1010.62 );
INSERT INTO Detalle VALUES( '000024', '000020', 2, 383.18 );
INSERT INTO Detalle VALUES( '000024', '000009', 1, 216.72 );
INSERT INTO Detalle VALUES( '000025', '000032', 1, 2040.00 );
INSERT INTO Detalle VALUES( '000025', '000036', 1, 430.40 );
INSERT INTO Detalle VALUES( '000026', '000020', 2, 383.18 );
INSERT INTO Detalle VALUES( '000026', '000018', 1, 185.30 );
INSERT INTO Detalle VALUES( '000027', '000024', 1, 2803.35 );
INSERT INTO Detalle VALUES( '000028', '000010', 1, 3094.28 );
INSERT INTO Detalle VALUES( '000029', '000012', 1, 712.14 );
INSERT INTO Detalle VALUES( '000029', '000009', 1, 216.72 );
INSERT INTO Detalle VALUES( '000030', '000015', 1, 113.24 );
INSERT INTO Detalle VALUES( '000030', '000016', 1, 169.83 );
INSERT INTO Detalle VALUES( '000030', '000017', 1, 204.68 );
INSERT INTO Detalle VALUES( '000031', '000028', 1, 2273.25 );
INSERT INTO Detalle VALUES( '000032', '000011', 1, 5360.14 );
INSERT INTO Detalle VALUES( '000033', '000031', 1, 2273.25 );
INSERT INTO Detalle VALUES( '000034', '000001', 1, 1838.40 );
INSERT INTO Detalle VALUES( '000034', '000002', 1, 2242.35 );
INSERT INTO Detalle VALUES( '000034', '000003', 1, 3095.37 );
INSERT INTO Detalle VALUES( '000035', '000038', 3, 1210.14 );
INSERT INTO Detalle VALUES( '000035', '000037', 1, 1202.85 );
INSERT INTO Detalle VALUES( '000036', '000020', 1, 191.59 );
INSERT INTO Detalle VALUES( '000037', '000015', 1, 113.24 );
INSERT INTO Detalle VALUES( '000038', '000014', 1, 415.86 );
INSERT INTO Detalle VALUES( '000039', '000010', 1, 3094.28 );
INSERT INTO Detalle VALUES( '000039', '000011', 1, 5360.14 );
INSERT INTO Detalle VALUES( '000040', '000030', 2, 412.80 );
INSERT INTO Detalle VALUES( '000040', '000038', 2, 806.76 );
INSERT INTO Detalle VALUES( '000041', '000008', 1, 2878.40 );
INSERT INTO Detalle VALUES( '000042', '000028', 1, 2273.25 );
INSERT INTO Detalle VALUES( '000043', '000023', 1, 8584.38 );
INSERT INTO Detalle VALUES( '000044', '000029', 1, 202.31 );
INSERT INTO Detalle VALUES( '000045', '000008', 2, 5756.80 );
INSERT INTO Detalle VALUES( '000046', '000007', 1, 4158.40 );
INSERT INTO Detalle VALUES( '000047', '000016', 3, 509.49 );
INSERT INTO Detalle VALUES( '000047', '000017', 2, 409.36 );
INSERT INTO Detalle VALUES( '000048', '000021', 1, 283.92 );
INSERT INTO Detalle VALUES( '000049', '000038', 1, 403.38 );
INSERT INTO Detalle VALUES( '000050', '000034', 1, 4318.40 );
INSERT INTO Detalle VALUES( '000051', '000009', 1, 216.72 );
INSERT INTO Detalle VALUES( '000051', '000010', 2, 6188.56 );
INSERT INTO Detalle VALUES( '000052', '000020', 1, 191.59 );
INSERT INTO Detalle VALUES( '000053', '000016', 1, 169.83 );
INSERT INTO Detalle VALUES( '000054', '000032', 1, 2040.00 );
INSERT INTO Detalle VALUES( '000054', '000033', 1, 3385.80 );
INSERT INTO Detalle VALUES( '000055', '000021', 2, 567.84 );
INSERT INTO Detalle VALUES( '000055', '000018', 2, 370.60 );
INSERT INTO Detalle VALUES( '000056', '000012', 1, 712.14 );
INSERT INTO Detalle VALUES( '000057', '000004', 1, 1868.30 );
INSERT INTO Detalle VALUES( '000058', '000015', 1, 113.24 );
INSERT INTO Detalle VALUES( '000059', '000015', 3, 339.72 );
INSERT INTO Detalle VALUES( '000059', '000016', 2, 339.66 );
INSERT INTO Detalle VALUES( '000060', '000019', 1, 519.97 );
INSERT INTO Detalle VALUES( '000061', '000030', 1, 206.40 );
INSERT INTO Detalle VALUES( '000061', '000031', 1, 2273.25 );
INSERT INTO Detalle VALUES( '000062', '000005', 1, 1474.36 );
INSERT INTO Detalle VALUES( '000063', '000030', 2, 412.80 );
INSERT INTO Detalle VALUES( '000063', '000029', 2, 1010.62 );
INSERT INTO Detalle VALUES( '000064', '000033', 1, 3385.80 );
INSERT INTO Detalle VALUES( '000065', '000002', 1, 2242.35 );
INSERT INTO Detalle VALUES( '000066', '000009', 1, 216.72 );
INSERT INTO Detalle VALUES( '000067', '000013', 3, 459.24 );
INSERT INTO Detalle VALUES( '000068', '000026', 1, 1318.35 );
INSERT INTO Detalle VALUES( '000068', '000025', 1, 1942.38 );
							 
							 
-- Tabla: Tipopago
INSERT INTO Tipopago VALUES( '0001', 'EFECTIVO' );
INSERT INTO Tipopago VALUES( '0002', 'TARJETA DE CREDITO' );
INSERT INTO Tipopago VALUES( '0003', 'TARJETA DE DEBITO' );
INSERT INTO Tipopago VALUES( '0004', 'CHEQUE' );
INSERT INTO Tipopago VALUES( '0005', 'NOTA DE CREDITO' );
INSERT INTO Tipopago VALUES( '0006', 'BONO EFECTIVO' );
							 
							 
--  Tabla: Contador
INSERT INTO Contador VALUES( 'ARTICULO', 38, 6 );
INSERT INTO Contador VALUES( 'EMPLEADO', 6, 4 );
INSERT INTO Contador VALUES( 'USUARIO', 3, 4 );
INSERT INTO Contador VALUES( 'CATEGORIA', 5, 4 );
INSERT INTO Contador VALUES( 'CLIENTE', 5, 6 );
INSERT INTO Contador VALUES( 'VENTA', 68, 6 );
INSERT INTO Contador VALUES( 'PAGO', 0, 6 );
INSERT INTO Contador VALUES( 'TIPO DE PAGO', 6, 4 );

COMMIT;