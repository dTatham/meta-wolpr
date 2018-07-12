DESCRIPTION = "Linux 4.17 Kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SCMVERSION ?= "y"

DEPENDS += "flo-atag-fixup"

#inherit kernel
require recipes-kernel/linux/linux-linaro-qcom.inc
require recipes-kernel/linux/linux-qcom-bootimg.inc

LOCALVERSION = "+wolpr"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
KBRANCH = "linux-4.17.y"
SRCREV = "d55bace9f728c5bbde96f14793d663c79534ad58"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=https;branch=${KBRANCH} \
           file://001-dts-qcom-apq8064-fix-gic_irq_domain_translate-warnings.patch \
           file://002-fix-dts-clocks.patch \
           file://003-dts-enable-riva.patch \
           file://004-dts-scm-clocks.patch \
	   file://cfg/usb-otg-ncm.cfg \
	   file://cfg/wcn36xx.cfg \
	   file://cfg/panel-dsi.cfg \
	   file://cfg/remoteproc.cfg \
           "
KERNEL_DEFCONFIG_apq8064 = "${S}/arch/arm/configs/qcom_defconfig"
KERNEL_CONFIG_FRAGMENTS = " \
	${WORKDIR}/cfg/usb-otg-ncm.cfg \
	${WORKDIR}/cfg/wcn36xx.cfg \
	${WORKDIR}/cfg/panel-dsi.cfg \
	${WORKDIR}/cfg/remoteproc.cfg \
"

KERNEL_MODULE_AUTOLOAD += " g_ncm "

do_compile_append_apq8064() {
	cp arch/${ARCH}/boot/zImage arch/${ARCH}/boot/zImage.backup
#	cat ${DEPLOY_DIR_IMAGE}/fixup.bin arch/${ARCH}/boot/zImage.backup > arch/${ARCH}/boot/zImage
	rm -f arch/${ARCH}/boot/zImage.backup
}
