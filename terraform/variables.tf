# Variables del provider MongoDB Atlas.
variable "atlas_public_key" {
  description = "MongoDB Atlas public API key"
  type        = string
  sensitive   = true
}

variable "atlas_private_key" {
  description = "MongoDB Atlas private API key"
  type        = string
  sensitive   = true
}

variable "atlas_org_id" {
  description = "MongoDB Atlas organization id"
  type        = string
}

# Variables del proyecto y cluster.
variable "atlas_project_name" {
  description = "Nombre del proyecto Atlas"
  type        = string
  default     = "prueba-tecnica-backend"
}

variable "atlas_cluster_name" {
  description = "Nombre del cluster Atlas"
  type        = string
  default     = "prueba-tecnica-cluster"
}

variable "atlas_region" {
  description = "Región del cluster en AWS"
  type        = string
  default     = "US_EAST_1"
}

variable "atlas_instance_size" {
  description = "Tamaño de instancia Atlas (ej: M10, M20)"
  type        = string
  default     = "M10"
}

# Variables de acceso de red y usuario de base de datos.
variable "allowed_cidr" {
  description = "CIDR permitido para acceder al cluster"
  type        = string
  default     = "0.0.0.0/0"
}

variable "db_username" {
  description = "Usuario de la base de datos"
  type        = string
  default     = "app_user"
}

variable "db_password" {
  description = "Password del usuario de base de datos"
  type        = string
  sensitive   = true
}

variable "db_name" {
  description = "Base de datos por defecto para la app"
  type        = string
  default     = "franchise_db"
}
