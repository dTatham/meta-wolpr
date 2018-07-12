include recipes-core/images/core-image-base.bb

IMAGE_FEATURES += " ssh-server-dropbear package-management "
IMAGE_INSTALL += " kernel-modules crda parted "

