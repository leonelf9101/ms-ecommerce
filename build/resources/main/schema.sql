CREATE TABLE PRODUCTOS (
                           id_producto VARCHAR(255) primary key,
                           nombre VARCHAR(250),
                           sku VARCHAR(250),
                           descripcion VARCHAR(250),
                           precio_sin_descuento DECIMAL(20,2),
                           tiene_descuento boolean
                       );

CREATE TABLE CARRITOS (
                           id_carrito VARCHAR(255) primary key,
                           estado VARCHAR(250)
);

CREATE TABLE CARRITOS_ITEMS (
            id_carrito_item VARCHAR(255) primary key,
            id_carrito VARCHAR(255),
            id_producto VARCHAR(255),
            cantidad int,
            foreign key (id_carrito) references CARRITOS(id_carrito),
            foreign key (id_producto) references PRODUCTOS(id_producto)
);