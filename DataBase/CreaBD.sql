/*
 *********************************************************
 *	Empresa			:	WilsonStore						 *
 *	Software		:	Sistema de Registro de Catálogo	 *
 *	DBMS			:	Oracle							 *
 *	Base de Datos	:	wilsonstore						 *
 *	Script			:	Crea la base de datos			 *
 *	Responsable		:	Christian Joel Benites Sánchez	 *
 *	Teléfono		:	(511) 992457747					 *
 *	Email			:	cbenitesanchez@gmail.com		 *
 *********************************************************
*/

-- ===========================================
-- Creación de la aplicación
-- ===========================================

CONNECT / AS sysdba

DECLARE n INT;
		command VARCHAR2(200);
BEGIN	command := 'DROP USER wilsonstore CASCADE';
		SELECT COUNT(*) INTO n
		FROM DBA_USERS
		WHERE username = 'WILSONSTORE';
		
		IF ( n = 1 ) THEN
			EXECUTE IMMEDIATE command;
		END IF;
END;
/

CREATE USER wilsonstore IDENTIFIED BY admin;
GRANT CONNECT, RESOURCE TO wilsonstore;

-- ===========================================
-- Conectarse a la aplicación
-- ===========================================

CONNECT wilsonstore/admin

-- ===========================================
-- Creación de los objetos de la base de datos
-- ===========================================

CREATE TABLE Empleado (
		emp_id			CHAR(4) NOT NULL,
		emp_paterno		VARCHAR2(25) NOT NULL,
		emp_materno		VARCHAR2(25) NOT NULL,
		emp_nombre		VARCHAR2(30) NOT NULL,
		emp_ciudad		VARCHAR2(25) NOT NULL,
		emp_direccion	VARCHAR2(50) NOT NULL,
		CONSTRAINT XPKEmpleado
			PRIMARY KEY ( emp_id )
);

CREATE TABLE Usuario (
		emp_id		CHAR(4) NOT NULL,
		usu_nombre	VARCHAR2(15) NOT NULL,
		usu_clave	VARCHAR2(15) NOT NULL,
		CONSTRAINT XPKUsuario
			PRIMARY KEY ( emp_id ),
		CONSTRAINT XFKUsuario
			FOREIGN KEY ( emp_id )
				REFERENCES Empleado,
		CONSTRAINT XUUsuario_Nombre
			UNIQUE ( usu_nombre )
);

CREATE TABLE Cliente (
		cli_id			CHAR(6) NOT NULL,
		cli_paterno		VARCHAR2(25) NOT NULL,
		cli_materno		VARCHAR2(25) NOT NULL,
		cli_nombre		VARCHAR2(30) NOT NULL,
		cli_ciudad		VARCHAR2(25) NOT NULL,
		cli_direccion	VARCHAR2(50) NOT NULL,
		CONSTRAINT XPKCliente
			PRIMARY KEY ( cli_id )
);

CREATE TABLE Categoria (
		cat_id		CHAR(4) NOT NULL,
		cat_nombre	VARCHAR2(50) NOT NULL,
		CONSTRAINT XPKCategoria
			PRIMARY KEY ( cat_id )
);

CREATE TABLE Articulo (
		art_id			CHAR(6) NOT NULL,
		art_nombre		VARCHAR2(60) NOT NULL,
		art_descrip		VARCHAR2(2000) NOT NULL,
		art_stock		NUMBER(3,0) NOT NULL,
		art_pcosto		NUMBER(6,2) NOT NULL,
		art_pctgan		NUMBER(3,2) NOT NULL,
		art_pventa		NUMBER(6,2) NOT NULL,
		art_poferta		NUMBER(6,2) NOT NULL,
		cat_id			CHAR(4) NOT NULL,
		CONSTRAINT XPKArticulo
			PRIMARY KEY ( art_id ),
		CONSTRAINT XFKArticulo
			FOREIGN KEY ( cat_id )
				REFERENCES Categoria,
		CONSTRAINT XUNombre_Articulo
			UNIQUE ( art_nombre ),
		CONSTRAINT XCPrecio_Costo_Min
			CHECK ( art_pcosto > 0.0 ),
		CONSTRAINT XCArticulo_PCTGanancia_Min
			CHECK ( art_pctgan >= 0.6 ),
		CONSTRAINT XCArticulo_Stock_Min
			CHECK ( art_stock >= 0 )
);

CREATE TABLE Venta (
	ven_id			CHAR(6) NOT NULL,
	ven_fecha		DATE NOT NULL,
	ven_subtotal	NUMBER(10,2) NOT NULL,
	ven_impuesto	NUMBER(10,2) NOT NULL,
	ven_total		NUMBER(10,2) NOT NULL,
	emp_id			CHAR(4) NOT NULL,
	cli_id			CHAR(6) NOT NULL,
	CONSTRAINT XPKVenta
		PRIMARY KEY ( ven_id ),
	CONSTRAINT XFKVenta_Empleado
		FOREIGN KEY ( emp_id )
			REFERENCES Empleado,
	CONSTRAINT XFKVenta_Cliente
		FOREIGN KEY ( cli_id )
			REFERENCES Cliente,
	CONSTRAINT XCSubtotal
		CHECK ( ven_subtotal > 0.0 ),
	CONSTRAINT XCImpuesto
		CHECK ( ven_impuesto > 0.0 ),
	CONSTRAINT XCTotal
		CHECK ( ven_total > 0.0 )
);

CREATE TABLE Detalle (
		ven_id		CHAR(6) NOT NULL,
		art_id		CHAR(6) NOT NULL,
		det_cant	NUMBER(3,0) NOT NULL,
		det_precio	NUMBER(10,2) NOT NULL,
		CONSTRAINT XPKDetalle
			PRIMARY KEY ( ven_id, art_id ),
		CONSTRAINT XFKDetalle_Venta
			FOREIGN KEY ( ven_id )
				REFERENCES Venta,
		CONSTRAINT XFKDetalle_Articulo
			FOREIGN KEY ( art_id )
				REFERENCES Articulo,
		CONSTRAINT XCCantidad
			CHECK ( det_cant > 0 ),
		CONSTRAINT XCPrecio
			CHECK ( det_precio > 0.0 )
);

CREATE TABLE Tipopago (
		tip_id		CHAR(4) NOT NULL,
		tip_nombre	VARCHAR2(50) NOT NULL,
		CONSTRAINT XPKTipopago
			PRIMARY KEY ( tip_id )
);

CREATE TABLE Pago (
		ven_id			CHAR(6) NOT NULL,
		pag_importe		NUMBER(10,2) NOT NULL,
		tip_id			CHAR(4) NOT NULL,
		CONSTRAINT XPKPago
			PRIMARY KEY ( ven_id ),
		CONSTRAINT XFKPago
			FOREIGN KEY ( ven_id )
				REFERENCES Venta,
		CONSTRAINT XFKPago_TipoPago
			FOREIGN KEY ( tip_id )
				REFERENCES Tipopago,
		CONSTRAINT XCImporte
			CHECK ( pag_importe > 0.0 )
);

CREATE TABLE Contador (
		con_tabla		VARCHAR2(30) NOT NULL,
		con_item		NUMBER(6,0) NOT NULL,
		con_longitud	NUMBER(3,0) NOT NULL,
		CONSTRAINT XPKContador
			PRIMARY KEY ( con_tabla )
);