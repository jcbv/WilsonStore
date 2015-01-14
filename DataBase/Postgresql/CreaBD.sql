/*
 *********************************************************
 *	Empresa			:	WilsonStore						 *
 *	Software		:	Sistema de Registro de Catálogo	 *
 *	DBMS			:	Postgresql						 *
 *	Base de Datos	:	wilsonstore						 *
 *	Script			:	Crea la base de datos			 *
 *	Responsable		:	Christian Joel Benites Sánchez	 *
 *	Teléfono		:	(511) 992457747					 *
 *	Email			:	cbenitesanchez@gmail.com		 *
 *********************************************************
*/

CREATE TABLE Empleado (
		emp_id			CHAR(4) NOT NULL,
		emp_paterno		VARCHAR(25) NOT NULL,
		emp_materno		VARCHAR(25) NOT NULL,
		emp_nombre		VARCHAR(30) NOT NULL,
		emp_ciudad		VARCHAR(25) NOT NULL,
		emp_direccion	VARCHAR(50) NOT NULL,
		CONSTRAINT XPKEmpleado
			PRIMARY KEY ( emp_id )
);

CREATE TABLE Usuario (
		emp_id		CHAR(4) NOT NULL,
		usu_nombre	VARCHAR(15) NOT NULL,
		usu_clave	VARCHAR(15) NOT NULL,
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
		cli_paterno		VARCHAR(25) NOT NULL,
		cli_materno		VARCHAR(25) NOT NULL,
		cli_nombre		VARCHAR(30) NOT NULL,
		cli_ciudad		VARCHAR(25) NOT NULL,
		cli_direccion	VARCHAR(50) NOT NULL,
		CONSTRAINT XPKCliente
			PRIMARY KEY ( cli_id )
);

CREATE TABLE Categoria (
		cat_id		CHAR(4) NOT NULL,
		cat_nombre	VARCHAR(50) NOT NULL,
		CONSTRAINT XPKCategoria
			PRIMARY KEY ( cat_id )
);

CREATE TABLE Articulo (
		art_id			CHAR(6) NOT NULL,
		art_nombre		VARCHAR(60) NOT NULL,
		art_descrip		VARCHAR(2000) NOT NULL,
		art_stock		NUMERIC(3,0) NOT NULL,
		art_pcosto		NUMERIC(6,2) NOT NULL,
		art_pctgan		NUMERIC(3,2) NOT NULL,
		art_pventa		NUMERIC(6,2) NOT NULL,
		art_poferta		NUMERIC(6,2) NOT NULL,
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
	ven_subtotal	NUMERIC(10,2) NOT NULL,
	ven_impuesto	NUMERIC(10,2) NOT NULL,
	ven_total		NUMERIC(10,2) NOT NULL,
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
		det_cant	NUMERIC(3,0) NOT NULL,
		det_precio	NUMERIC(10,2) NOT NULL,
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
		tip_nombre	VARCHAR(50) NOT NULL,
		CONSTRAINT XPKTipopago
			PRIMARY KEY ( tip_id )
);

CREATE TABLE Pago (
		ven_id			CHAR(6) NOT NULL,
		pag_importe		NUMERIC(10,2) NOT NULL,
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
		con_tabla		VARCHAR(30) NOT NULL,
		con_item		NUMERIC(6,0) NOT NULL,
		con_longitud	NUMERIC(3,0) NOT NULL,
		CONSTRAINT XPKContador
			PRIMARY KEY ( con_tabla )
);