# Final Testing II - Carrera Certified Tech Developer

## Indice

- [Objetivo](#objetivo)
- [Sistema Bajo Prueba](#sistema-bajo-prueba)
- [Pruebas Front-end](#pruebas-front-end)
    - [Registro](#registro)
    - [Abrir una nueva cuenta](#abrir-nueva-cuenta)
    - [Resumen de las cuentas](#resumen-de-las-cuentas)
    - [Transferir fondos](#transferir-fondos)
    - [Actividad de la cuenta (cada mes)](#actividad-de-la-cuenta)
- [Pruebas Back-end](#pruebas-back-end)
    - [Registro](#registro-back-end)
    - [Abrir una nueva cuenta](#abrir-nueva-cuenta-back-end)
    - [Resumen de las cuentas](#resumen-de-las-cuentas-back-end)
    - [Transferir fondos](#transferir-fondos-back-end)
    - [Actividad de la cuenta (cada mes)](#actividad-de-la-cuenta-back-end)


## Objetivo

Crear un proyecto completo de Pruebas Automatizadas usando _Java_ y _Selenium_, con Suites,
Etiquetas e Informes, validando también una API con _RestAssured_.

## Sistema bajo prueba 
[ParaBank](https://parabank.parasoft.com/parabank/index.htm)

Crear un proyecto completo de Pruebas Automatizadas usando _Java_ y _Selenium_, con Suites,
Etiquetas e Informes, validando también una API con _RestAssured_.

## Pruebas Front-end

_Las siguientes condiciones son requisitos mínimos necesarios para la aprobación de cada caso de prueba:_

### Registro

    * Haga clic en <Registro>
    * Rellene el formulario de registro con los datos requeridos
    * Pulse de nuevo en <Registro>
    * Compruebe que el texto "Su cuenta se ha creado correctamente. En la pantalla se puede ver "You are now logged in".

### Abrir una nueva cuenta

    * Haga clic en <Abrir nueva cuenta>
    * En el apartado "¿Qué tipo de cuenta desea abrir?" seleccione la opción <SAVINGS>.
    * Haga clic en <Abrir nueva cuenta> 
    * Compruebe si el texto "Congratulations, your account is now open." está visible en la pantalla

### Resumen de las cuentas

    * Haga clic en <Resumen de cuentas>
    * Compruebe si el texto "*El saldo incluye depósitos que pueden estar sujetos a retenciones" está visible en la pantalla

### Transferir fondos

    * Haga clic en <Transferencia de fondos>
    * Compruebe que el texto "Transferir fondos" es visible en la pantalla
    * En el campo <Importe: $> introduzca el importe a transferir
    * En el campo <De la cuenta #> seleccione una cuenta
    * En el campo <a la cuenta #> seleccione una cuenta distinta a la anterior
    * Haga clic en <Transferencia>
    * Compruebe que el texto "¡Transferencia completa!" es visible en la pantalla

### Actividad de la cuenta (cada mes)

    * Haga clic en <Resumen de cuentas>
    * Compruebe que el texto "*El saldo incluye depósitos que pueden estar sujetos a
    retenciones" es visible en la pantalla
    * Haga clic en una cuenta en la columna <Cuenta>
    * Compruebe que el texto "Detalles de la cuenta" es visible en la pantalla
    * En "Actividad de la cuenta" haga clic en <Periodo de actividad:> y seleccione "Todo"
    * En "Actividad de la cuenta" haga clic en <Tipo:> y seleccione "Todo"
    * Haga clic en <Ir>

## Pruebas Back-end

_Validación del código de estado 200 para todas las etapas de las pruebas_

### Registro URL: https://parabank.parasoft.com/parabank/register.htm

### Abrir una nueva cuenta URL: https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx

### Resumen de las cuentas URL: https://parabank.parasoft.com/parabank/overview.htm

### Descarga de fondos URL: https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx

### Actividad de la cuenta (cada mes) URL: https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All


## Dependencias

- Selenium
- Rest Assured
