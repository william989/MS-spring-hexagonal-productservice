CREATE TABLE IF NOT EXISTS public.products (
    id bigint NOT NULL,
    name varchar(30) NOT NULL,
    description varchar(200) NOT NULL,
    price NUMERIC(5, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.seq_products START WITH 1 INCREMENT BY 1 MAXVALUE 9999999 MINVALUE 1;
ALTER SEQUENCE public.seq_products OWNED BY public.products.id;