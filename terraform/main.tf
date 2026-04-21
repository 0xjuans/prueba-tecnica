provider "mongodbatlas" {
  public_key  = var.atlas_public_key
  private_key = var.atlas_private_key
}

resource "mongodbatlas_project" "this" {
  name   = var.atlas_project_name
  org_id = var.atlas_org_id
}

resource "mongodbatlas_advanced_cluster" "this" {
  project_id   = mongodbatlas_project.this.id
  name         = var.atlas_cluster_name
  cluster_type = "REPLICASET"

  replication_specs {
    region_configs {
      provider_name = "AWS"
      region_name   = var.atlas_region
      priority      = 7

      electable_specs {
        instance_size = var.atlas_instance_size
        node_count    = 3
      }
    }
  }
}

resource "mongodbatlas_project_ip_access_list" "allow_cidr" {
  project_id = mongodbatlas_project.this.id
  cidr_block = var.allowed_cidr
  comment    = "Access for backend/API"
}

resource "mongodbatlas_database_user" "app_user" {
  project_id         = mongodbatlas_project.this.id
  username           = var.db_username
  password           = var.db_password
  auth_database_name = "admin"

  roles {
    role_name     = "readWrite"
    database_name = var.db_name
  }

  scopes {
    name = mongodbatlas_advanced_cluster.this.name
    type = "CLUSTER"
  }
}
