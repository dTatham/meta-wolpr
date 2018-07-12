DESCRIPTION = "Simple helloworld application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://www.codeaurora.org/cgit/quic/kernel/skales/plain/atag-fix/fixup.S"
SRC_URI[sha256sum] = "9768d10e049423ac3c095984cf1a9b17513ef545485a43bc44eb0ffb3477fc8b"

inherit deploy

do_compile_prepend() {
	cp ${WORKDIR}/fixup.S ${S}
}

do_compile() {
	${CC} -c fixup.S -o fixup.o
	${OBJCOPY} -O binary fixup.o fixup.bin
}

addtask deploy after do_compile

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0755 fixup.bin ${DEPLOYDIR}/fixup.bin
}
