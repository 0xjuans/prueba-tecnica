# Terraform - Provisionamiento de persistencia (MongoDB Atlas)

Este directorio aprovisiona la capa de persistencia en MongoDB Atlas usando Terraform:

- Proyecto Atlas
- Cluster Atlas (replicaset)
- Regla de acceso por CIDR
- Usuario de base de datos para la aplicación

## Requisitos

- Terraform >= 1.6
- Cuenta MongoDB Atlas
- Atlas API public/private keys
- Organization ID de Atlas

## Uso

1. Copia el archivo de ejemplo y completa valores reales:

   cp terraform.tfvars.example terraform.tfvars

2. Inicializa Terraform:

   terraform init

3. Revisa el plan:

   terraform plan

4. Aplica los cambios:

   terraform apply

5. Obtén outputs:

   terraform output

## Variables sensibles

No subas `terraform.tfvars` al repo. Usa ese archivo solo en local/CI seguro.

## Notas

- El CIDR por defecto `0.0.0.0/0` es solo para pruebas.
- Para entornos reales, restringe IPs y gestiona secretos con un vault.
