package freechips.rocketchip.devices.debug

import chisel3._

// This file was auto-generated from the repository at https://github.com/riscv/riscv-debug-spec.git,
// 'make chisel'

object DM_RegAddrs {
  /* This register reports status for the overall Debug Module as well as
        the currently selected harts, as defined in \FdmDmcontrolHasel.  Its address will
        not change in the future, because it contains \FdmDmstatusVersion.
  */
  def DM_DMSTATUS =  0x11

  /* This register controls the overall Debug Module
        as well as the currently selected harts, as defined in \FdmDmcontrolHasel.

\label{hartsel}
\index{hartsel}
        Throughout this document we refer to \Fhartsel, which is \FdmDmcontrolHartselhi
        combined with \FdmDmcontrolHartsello. While the spec allows for 20 \Fhartsel bits,
        an implementation may choose to implement fewer than that. The actual
        width of \Fhartsel is called {\tt HARTSELLEN}. It must be at least 0
        and at most 20. A debugger should discover {\tt HARTSELLEN} by writing
        all ones to \Fhartsel (assuming the maximum size) and reading back the
        value to see which bits were actually set. Debuggers must not change
        \Fhartsel while an abstract command is executing.

        \begin{commentary}
        There are separate \FdmDmcontrolSetresethaltreq and \FdmDmcontrolClrresethaltreq bits so that
        it is possible to write \RdmDmcontrol without changing the halt-on-reset
        request bit for each selected hart, when not all selected harts have
        the same configuration.
        \end{commentary}

        On any given write, a debugger may only write 1 to at most one of the
        following bits: \FdmDmcontrolResumereq, \FdmDmcontrolHartreset, \FdmDmcontrolAckhavereset,
        \FdmDmcontrolSetresethaltreq, and \FdmDmcontrolClrresethaltreq. The others must be written 0.

\label{resethaltreq}
\index{resethaltreq}
        \Fresethaltreq is an optional internal bit of per-hart state that cannot be
        read, but can be written with \FdmDmcontrolSetresethaltreq and \FdmDmcontrolClrresethaltreq.

\label{keepalive}
\index{keepalive}
        \Fkeepalive is an optional internal bit of per-hart state. When it is
        set, it suggests that the hardware should attempt to keep the hart
        available for the debugger, e.g. by keeping it from entering a
        low-power state once powered on. Even if the bit is implemented,
        hardware might not be able to keep a hart available. The bit is
        written through \FdmDmcontrolSetkeepalive and
        \FdmDmcontrolClrkeepalive.

        For forward compatibility, \FdmDmstatusVersion will always be readable when bit 1
        (\FdmDmcontrolNdmreset) is 0 and bit 0 (\FdmDmcontrolDmactive) is 1.
  */
  def DM_DMCONTROL =  0x10

  /* This register gives information about the hart currently
      selected by \Fhartsel.

      This register is optional. If it is not present it should
      read all-zero.

      If this register is included, the debugger can do more with
      the Program Buffer by writing programs which
      explicitly access the {\tt data} and/or {\tt dscratch}
      registers.
  */
  def DM_HARTINFO =  0x12

  /* This register selects which of the 32-bit portion of the hart array mask
      register (see Section~\ref{hartarraymask}) is accessible in \RdmHawindow.
  */
  def DM_HAWINDOWSEL =  0x14

  /* This register provides R/W access to a 32-bit portion of the
      hart array mask register (see Section~\ref{hartarraymask}).
      The position of the window is determined by \RdmHawindowsel. I.e. bit 0
      refers to hart $\RdmHawindowsel * 32$, while bit 31 refers to hart
      $\RdmHawindowsel * 32 + 31$.

      Since some bits in the hart array mask register may be constant 0, some
      bits in this register may be constant 0, depending on the current value
      of \FdmHawindowselHawindowsel.
  */
  def DM_HAWINDOW =  0x15

  /* Writing this register while an abstract command is executing causes
        \FdmAbstractcsCmderr to become 1 (busy) once the command completes
        (busy becomes 0).

        \begin{commentary}
            \FdmAbstractcsDatacount must be at least 1 to support RV32 harts, 2 to support
            RV64 harts, or 4 to support RV128 harts.
        \end{commentary}
  */
  def DM_ABSTRACTCS =  0x16

  /* Writes to this register cause the corresponding abstract command to be
        executed.

        Writing this register while an abstract command is executing causes
        \FdmAbstractcsCmderr to become 1 (busy) once the command completes
        (busy becomes 0).

        If \FdmAbstractcsCmderr is non-zero, writes to this register are ignored.

        \begin{commentary}
            \FdmAbstractcsCmderr inhibits starting a new command to accommodate debuggers
            that, for performance reasons, send several commands to be executed
            in a row without checking \FdmAbstractcsCmderr in between. They can safely do
            so and check \FdmAbstractcsCmderr at the end without worrying that one command
            failed but then a later command (which might have depended on the
            previous one succeeding) passed.
        \end{commentary}
  */
  def DM_COMMAND =  0x17

  /* This register is optional. Including it allows more efficient burst
        accesses.  A debugger can detect whether it is supported by setting bits
        and reading them back.

        If this register is implemented then bits corresponding to implemented
        progbuf and data registers must be writable.  Other bits must be
        hard-wired to 0.

        Writing this register while an abstract command is executing causes
        \FdmAbstractcsCmderr to become 1 (busy) once the command completes
        (busy becomes 0).
  */
  def DM_ABSTRACTAUTO =  0x18

  /* When \FdmDmstatusConfstrptrvalid is set, reading this register returns bits 31:0
      of the configuration string pointer. Reading the other {\tt confstrptr}
      registers returns the upper bits of the address.

      When system bus mastering is implemented, this must be an
      address that can be used with the System Bus Access module. Otherwise,
      this must be an address that can be used to access the
      configuration string from the hart with ID 0.

      If \FdmDmstatusConfstrptrvalid is 0, then the {\tt confstrptr} registers
      hold identifier information which is not
      further specified in this document.

      The configuration string itself is described in the Privileged Spec.
  */
  def DM_CONFSTRPTR0 =  0x19

  /* When \FdmDmstatusConfstrptrvalid is set, reading this register returns bits 63:32
      of the configuration string pointer. See \RdmConfstrptrZero for more details.
  */
  def DM_CONFSTRPTR1 =  0x1a

  /* When \FdmDmstatusConfstrptrvalid is set, reading this register returns bits 95:64
      of the configuration string pointer. See \RdmConfstrptrZero for more details.
  */
  def DM_CONFSTRPTR2 =  0x1b

  /* When \FdmDmstatusConfstrptrvalid is set, reading this register returns bits 127:96
      of the configuration string pointer. See \RdmConfstrptrZero for more details.
  */
  def DM_CONFSTRPTR3 =  0x1c

  /* If there is more than one DM accessible on this DMI, this register
        contains the base address of the next one in the chain, or 0 if this is
        the last one in the chain.
  */
  def DM_NEXTDM =  0x1d

  /* \RdmDataZero through \RdmDataEleven are basic read/write registers that may
        be read or changed by abstract commands. \FdmAbstractcsDatacount indicates how many
        of them are implemented, starting at \RdmDataZero, counting up.
        Table~\ref{tab:datareg} shows how abstract commands use these
        registers.

        Accessing these registers while an abstract command is executing causes
        \FdmAbstractcsCmderr to be set to 1 (busy) if it is 0.

        Attempts to write them while \FdmAbstractcsBusy is set does not change their value.

        The values in these registers might not be preserved after an abstract
        command is executed. The only guarantees on their contents are the ones
        offered by the command in question. If the command fails, no
        assumptions can be made about the contents of these registers.
  */
  def DM_DATA0 =  0x04

  def DM_DATA1 =  0x05

  def DM_DATA2 =  0x06

  def DM_DATA3 =  0x07

  def DM_DATA4 =  0x08

  def DM_DATA5 =  0x09

  def DM_DATA6 =  0x0a

  def DM_DATA7 =  0x0b

  def DM_DATA8 =  0x0c

  def DM_DATA9 =  0x0d

  def DM_DATA10 =  0x0e

  def DM_DATA11 =  0x0f

  /* \RdmProgbufZero through \RdmProgbufFifteen provide read/write access to the
        optional program buffer. \FdmAbstractcsProgbufsize indicates how many of them are
        implemented starting at \RdmProgbufZero, counting up.

        Accessing these registers while an abstract command is executing causes
        \FdmAbstractcsCmderr to be set to 1 (busy) if it is 0.

        Attempts to write them while \FdmAbstractcsBusy is set does not change their value.
  */
  def DM_PROGBUF0 =  0x20

  def DM_PROGBUF1 =  0x21

  def DM_PROGBUF2 =  0x22

  def DM_PROGBUF3 =  0x23

  def DM_PROGBUF4 =  0x24

  def DM_PROGBUF5 =  0x25

  def DM_PROGBUF6 =  0x26

  def DM_PROGBUF7 =  0x27

  def DM_PROGBUF8 =  0x28

  def DM_PROGBUF9 =  0x29

  def DM_PROGBUF10 =  0x2a

  def DM_PROGBUF11 =  0x2b

  def DM_PROGBUF12 =  0x2c

  def DM_PROGBUF13 =  0x2d

  def DM_PROGBUF14 =  0x2e

  def DM_PROGBUF15 =  0x2f

  /* This register serves as a 32-bit serial port to/from the authentication
        module.

        When \FdmDmstatusAuthbusy is clear, the debugger can communicate with the
        authentication module by reading or writing this register. There is no
        separate mechanism to signal overflow/underflow.
  */
  def DM_AUTHDATA =  0x30

  /* This register contains DM control and status bits that didn't easily
        fit in \RdmDmcontrol and \RdmDmstatus. All are optional.

        If halt groups are not implemented, then \FdmDmcsTwoGroup will always
        be 0 when \FdmDmcsTwoGrouptype is 0.

        If resume groups are not implemented, then \FdmDmcsTwoGrouptype will
        remain 0 even after 1 is written there.

        The DM external triggers available to add to halt groups may be the same as
        or distinct from the DM external triggers available to add to resume groups.
  */
  def DM_DMCS2 =  0x32

  /* Each bit in this read-only register indicates whether one specific hart
        is halted or not. Unavailable/nonexistent harts are not considered to
        be halted.

        This register might not be present if fewer than 2 harts are connected
        to this DM.

        The LSB reflects the halt status of hart \{hartsel[19:5],5'h0\}, and the
        MSB reflects halt status of hart \{hartsel[19:5],5'h1f\}.
  */
  def DM_HALTSUM0 =  0x40

  /* Each bit in this read-only register indicates whether any of a group of
        harts is halted or not. Unavailable/nonexistent harts are not considered to
        be halted.

        This register might not be present if fewer than 33 harts are connected
        to this DM.

        The LSB reflects the halt status of harts \{hartsel[19:10],10'h0\}
        through \{hartsel[19:10],10'h1f\}.
        The MSB reflects the halt status of harts \{hartsel[19:10],10'h3e0\}
        through \{hartsel[19:10],10'h3ff\}.
  */
  def DM_HALTSUM1 =  0x13

  /* Each bit in this read-only register indicates whether any of a group of
        harts is halted or not. Unavailable/nonexistent harts are not considered to
        be halted.

        This register might not be present if fewer than 1025 harts are connected
        to this DM.

        The LSB reflects the halt status of harts \{hartsel[19:15],15'h0\}
        through \{hartsel[19:15],15'h3ff\}.
        The MSB reflects the halt status of harts \{hartsel[19:15],15'h7c00\}
        through \{hartsel[19:15],15'h7fff\}.
  */
  def DM_HALTSUM2 =  0x34

  /* Each bit in this read-only register indicates whether any of a group of
        harts is halted or not. Unavailable/nonexistent harts are not considered to
        be halted.

        This register might not be present if fewer than 32769 harts are connected
        to this DM.

        The LSB reflects the halt status of harts 20'h0 through 20'h7fff.
        The MSB reflects the halt status of harts 20'hf8000 through 20'hfffff.
  */
  def DM_HALTSUM3 =  0x35

  def DM_SBCS =  0x38

  /* If \FdmSbcsSbasize is 0, then this register is not present.

        When the system bus master is busy, writes to this register will set
        \FdmSbcsSbbusyerror and don't do anything else.

        \begin{steps}{If \FdmSbcsSberror is 0, \FdmSbcsSbbusyerror is 0, and \FdmSbcsSbreadonaddr
        is set then writes to this register start the following:}
            \item Set \FdmSbcsSbbusy.
            \item Perform a bus read from the new value of {\tt sbaddress}.
            \item If the read succeeded and \FdmSbcsSbautoincrement is set, increment
            {\tt sbaddress}.
            \item Clear \FdmSbcsSbbusy.
        \end{steps}
  */
  def DM_SBADDRESS0 =  0x39

  /* If \FdmSbcsSbasize is less than 33, then this register is not present.

        When the system bus master is busy, writes to this register will set
        \FdmSbcsSbbusyerror and don't do anything else.
  */
  def DM_SBADDRESS1 =  0x3a

  /* If \FdmSbcsSbasize is less than 65, then this register is not present.

        When the system bus master is busy, writes to this register will set
        \FdmSbcsSbbusyerror and don't do anything else.
  */
  def DM_SBADDRESS2 =  0x3b

  /* If \FdmSbcsSbasize is less than 97, then this register is not present.

        When the system bus master is busy, writes to this register will set
        \FdmSbcsSbbusyerror and don't do anything else.
  */
  def DM_SBADDRESS3 =  0x37

  /* If all of the {\tt sbaccess} bits in \RdmSbcs are 0, then this register
        is not present.

        Any successful system bus read updates {\tt sbdata}. If the width of
        the read access is less than the width of {\tt sbdata}, the contents of
        the remaining high bits may take on any value.

        If either \FdmSbcsSberror or \FdmSbcsSbbusyerror isn't 0 then accesses do nothing.

        If the bus master is busy then accesses set \FdmSbcsSbbusyerror, and don't do
        anything else.

        \begin{steps}{Writes to this register start the following:}
            \item Set \FdmSbcsSbbusy.
            \item Perform a bus write of the new value of {\tt sbdata} to {\tt sbaddress}.
            \item If the write succeeded and \FdmSbcsSbautoincrement is set,
            increment {\tt sbaddress}.
            \item Clear \FdmSbcsSbbusy.
        \end{steps}

        \begin{steps}{Reads from this register start the following:}
            \item ``Return'' the data.
            \item Set \FdmSbcsSbbusy.
            \item \begin{steps}{If \FdmSbcsSbreadondata is set:}
                \item Perform a system bus read from the address contained in
                        {\tt sbaddress}, placing the result in {\tt sbdata}.
                \item If \FdmSbcsSbautoincrement is set and the read was
                        successful, increment {\tt sbaddress}.
            \end{steps}
            \item Clear \FdmSbcsSbbusy.
        \end{steps}

        Only \RdmSbdataZero has this behavior. The other {\tt sbdata} registers
        have no side effects. On systems that have buses wider than 32 bits, a
        debugger should access \RdmSbdataZero after accessing the other {\tt
        sbdata} registers.
  */
  def DM_SBDATA0 =  0x3c

  /* If \FdmSbcsSbaccessSixtyfour and \FdmSbcsSbaccessOneTwentyeight are 0, then this
        register is not present.

        If the bus master is busy then accesses set \FdmSbcsSbbusyerror, and don't do
        anything else.
  */
  def DM_SBDATA1 =  0x3d

  /* This register only exists if \FdmSbcsSbaccessOneTwentyeight is 1.

        If the bus master is busy then accesses set \FdmSbcsSbbusyerror, and don't do
        anything else.
  */
  def DM_SBDATA2 =  0x3e

  /* This register only exists if \FdmSbcsSbaccessOneTwentyeight is 1.

        If the bus master is busy then accesses set \FdmSbcsSbbusyerror, and don't do
        anything else.
  */
  def DM_SBDATA3 =  0x3f

  /* This optional register may be used for non-standard features. Future
        version of the debug spec will not use this address.
  */
  def DM_CUSTOM =  0x1f

  /* The optional \RdmCustomZero through \RdmCustomFifteen registers may
        be used for non-standard features. Future versions of the debug spec
        will not use these addresses.
  */
  def DM_CUSTOM0 =  0x70

  def DM_CUSTOM1 =  0x71

  def DM_CUSTOM2 =  0x72

  def DM_CUSTOM3 =  0x73

  def DM_CUSTOM4 =  0x74

  def DM_CUSTOM5 =  0x75

  def DM_CUSTOM6 =  0x76

  def DM_CUSTOM7 =  0x77

  def DM_CUSTOM8 =  0x78

  def DM_CUSTOM9 =  0x79

  def DM_CUSTOM10 =  0x7a

  def DM_CUSTOM11 =  0x7b

  def DM_CUSTOM12 =  0x7c

  def DM_CUSTOM13 =  0x7d

  def DM_CUSTOM14 =  0x7e

  def DM_CUSTOM15 =  0x7f

}

class DMSTATUSFields extends Bundle {

  val reserved0 = UInt(7.W)

  /* 0: Unimplemented, or \FdmDmcontrolNdmreset is zero and no ndmreset is currently 
            in progress.

            1: \FdmDmcontrolNdmreset is currently nonzero, or there is an ndmreset in progress.
  */
  val ndmresetpending = Bool()

  /* 0: The per-hart {\tt unavail} bits reflect the current state of the hart.

            1: The per-hart {\tt unavail} bits are sticky. Once they are set, they will
            not clear until the debugger acknowledges them using \FdmDmcontrolAckunavail.
  */
  val stickyunavail = Bool()

  /* If 1, then there is an implicit {\tt ebreak} instruction at the
            non-existent word immediately after the Program Buffer. This saves
            the debugger from having to write the {\tt ebreak} itself, and
            allows the Program Buffer to be one word smaller.

            This must be 1 when \FdmAbstractcsProgbufsize is 1.
  */
  val impebreak = Bool()

  val reserved1 = UInt(2.W)

  /* This field is 1 when all currently selected harts have been reset
            and reset has not been acknowledged for any of them.
  */
  val allhavereset = Bool()

  /* This field is 1 when at least one currently selected hart has been
            reset and reset has not been acknowledged for that hart.
  */
  val anyhavereset = Bool()

  /* This field is 1 when all currently selected harts have acknowledged
            their last resume request.
  */
  val allresumeack = Bool()

  /* This field is 1 when any currently selected hart has acknowledged
            its last resume request.
  */
  val anyresumeack = Bool()

  /* This field is 1 when all currently selected harts do not exist in
            this hardware platform.
  */
  val allnonexistent = Bool()

  /* This field is 1 when any currently selected hart does not exist in
            this hardware platform.
  */
  val anynonexistent = Bool()

  /* This field is 1 when all currently selected harts are
            unavailable, or (if \FdmDmstatusStickyunavail is 1) were
            unavailable without that being acknowledged.
  */
  val allunavail = Bool()

  /* This field is 1 when any currently selected hart is unavailable,
            or (if \FdmDmstatusStickyunavail is 1) was unavailable without
            that being acknowledged.
  */
  val anyunavail = Bool()

  /* This field is 1 when all currently selected harts are running.
  */
  val allrunning = Bool()

  /* This field is 1 when any currently selected hart is running.
  */
  val anyrunning = Bool()

  /* This field is 1 when all currently selected harts are halted.
  */
  val allhalted = Bool()

  /* This field is 1 when any currently selected hart is halted.
  */
  val anyhalted = Bool()

  /* 0: Authentication is required before using the DM.

            1: The authentication check has passed.

            On components that don't implement authentication, this bit must be
            preset as 1.
  */
  val authenticated = Bool()

  /* 0: The authentication module is ready to process the next
            read/write to \RdmAuthdata.

            1: The authentication module is busy. Accessing \RdmAuthdata results
            in unspecified behavior.

            \FdmDmstatusAuthbusy only becomes set in immediate response to an access to
            \RdmAuthdata.
  */
  val authbusy = Bool()

  /* 1 if this Debug Module supports halt-on-reset functionality
            controllable by the \FdmDmcontrolSetresethaltreq and \FdmDmcontrolClrresethaltreq bits.
            0 otherwise.
  */
  val hasresethaltreq = Bool()

  /* 0: \RdmConfstrptrZero--\RdmConfstrptrThree hold information which
            is not relevant to the configuration string.

            1: \RdmConfstrptrZero--\RdmConfstrptrThree hold the address of the
            configuration string.
  */
  val confstrptrvalid = Bool()

  /* 0: There is no Debug Module present.

            1: There is a Debug Module and it conforms to version 0.11 of this
            specification.

            2: There is a Debug Module and it conforms to version 0.13 of this
            specification.

            3: There is a Debug Module and it conforms to version 1.0 of this
            specification.

            15: There is a Debug Module but it does not conform to any
            available version of this spec.
  */
  val version = UInt(4.W)

}

class DMCONTROLFields extends Bundle {

  /* Writing 0 clears the halt request bit for all currently selected
            harts. This may cancel outstanding halt requests for those harts.

            Writing 1 sets the halt request bit for all currently selected
            harts. Running harts will halt whenever their halt request bit is
            set.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val haltreq = Bool()

  /* Writing 1 causes the currently selected harts to resume once, if
            they are halted when the write occurs. It also clears the resume
            ack bit for those harts.

            \FdmDmcontrolResumereq is ignored if \FdmDmcontrolHaltreq is set.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val resumereq = Bool()

  /* This optional field writes the reset bit for all the currently
            selected harts.  To perform a reset the debugger writes 1, and then
            writes 0 to deassert the reset signal.

            While this bit is 1, the debugger must not change which harts are
            selected.

            If this feature is not implemented, the bit always stays 0, so
            after writing 1 the debugger can read the register back to see if
            the feature is supported.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val hartreset = Bool()

  /* 0: No effect.

            1: Clears {\tt havereset} for any selected harts.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val ackhavereset = Bool()

  /* 0: No effect.

            1: Clears {\tt unavail} for any selected harts.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val ackunavail = Bool()

  /* Selects the definition of currently selected harts.

            0: There is a single currently selected hart, that is selected by \Fhartsel.

            1: There may be multiple currently selected harts -- the hart
            selected by \Fhartsel, plus those selected by the hart array mask
            register.

            An implementation which does not implement the hart array mask register
            must tie this field to 0. A debugger which wishes to use the hart array
            mask register feature should set this bit and read back to see if the functionality
            is supported.
  */
  val hasel = Bool()

  /* The low 10 bits of \Fhartsel: the DM-specific index of the hart to
            select. This hart is always part of the currently selected harts.
  */
  val hartsello = UInt(10.W)

  /* The high 10 bits of \Fhartsel: the DM-specific index of the hart to
            select. This hart is always part of the currently selected harts.
  */
  val hartselhi = UInt(10.W)

  /* This optional field sets \Fkeepalive for all currently selected
            harts, unless \FdmDmcontrolClrkeepalive is simultaneously set to
            1.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val setkeepalive = Bool()

  /* This optional field clears \Fkeepalive for all currently selected
            harts.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val clrkeepalive = Bool()

  /* This optional field writes the halt-on-reset request bit for all
            currently selected harts, unless \FdmDmcontrolClrresethaltreq is
            simultaneously set to 1.
            When set to 1, each selected hart will halt upon the next deassertion
            of its reset. The halt-on-reset request bit is not automatically
            cleared. The debugger must write to \FdmDmcontrolClrresethaltreq to clear it.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.

            If \FdmDmstatusHasresethaltreq is 0, this field is not implemented.
  */
  val setresethaltreq = Bool()

  /* This optional field clears the halt-on-reset request bit for all
            currently selected harts.

            Writes apply to the new value of \Fhartsel and \FdmDmcontrolHasel.
  */
  val clrresethaltreq = Bool()

  /* This bit controls the reset signal from the DM to the rest of the
            hardware platform. The signal should reset every part of the hardware platform, including
            every hart, except for the DM and any logic required to access the
            DM.
            To perform a hardware platform reset the debugger writes 1,
            and then writes 0
            to deassert the reset.
  */
  val ndmreset = Bool()

  /* This bit serves as a reset signal for the Debug Module itself.
            After changing the value of this bit, the debugger must poll
            \RdmDmcontrol until \FdmDmcontrolDmactive has taken the requested value
            before performing any action that assumes the requested \FdmDmcontrolDmactive
            state change has completed.  Hardware may
            take an arbitrarily long time to complete activation or deactivation and will
            indicate completion by setting \FdmDmcontrolDmactive to the requested value.

            0: The module's state, including authentication mechanism,
            takes its reset values (the \FdmDmcontrolDmactive bit is the only bit which can
            be written to something other than its reset value). Any accesses
            to the module may fail. Specifically, \FdmDmstatusVersion might not return
            correct data.

            1: The module functions normally.

            No other mechanism should exist that may result in resetting the
            Debug Module after power up.

            To place the Debug Module into a known state, a debugger may write 0 to \FdmDmcontrolDmactive,
            poll until \FdmDmcontrolDmactive is observed 0, write 1 to \FdmDmcontrolDmactive, and
            poll until \FdmDmcontrolDmactive is observed 1.

            Implementations may pay attention to this bit to further aid
            debugging, for example by preventing the Debug Module from being
            power gated while debugging is active.
  */
  val dmactive = Bool()

}

class HARTINFOFields extends Bundle {

  val reserved0 = UInt(8.W)

  /* Number of {\tt dscratch} registers available for the debugger
            to use during program buffer execution, starting from \RcsrDscratchZero.
            The debugger can make no assumptions about the contents of these
            registers between commands.
  */
  val nscratch = UInt(4.W)

  val reserved1 = UInt(3.W)

  /* 0: The {\tt data} registers are shadowed in the hart by CSRs.
            Each CSR is DXLEN bits in size, and corresponds
            to a single argument, per Table~\ref{tab:datareg}.

            1: The {\tt data} registers are shadowed in the hart's memory map.
            Each register takes up 4 bytes in the memory map.
  */
  val dataaccess = Bool()

  /* If \FdmHartinfoDataaccess is 0: Number of CSRs dedicated to
            shadowing the {\tt data} registers.

            If \FdmHartinfoDataaccess is 1: Number of 32-bit words in the memory map
            dedicated to shadowing the {\tt data} registers.

            Since there are at most 12 {\tt data} registers, the value in this
            register must be 12 or smaller.
  */
  val datasize = UInt(4.W)

  /* If \FdmHartinfoDataaccess is 0: The number of the first CSR dedicated to
            shadowing the {\tt data} registers.

            If \FdmHartinfoDataaccess is 1: Address of RAM where the data
            registers are shadowed. This address is sign extended giving a
            range of -2048 to 2047, easily addressed with a load or store using
            \Xzero as the address register.
  */
  val dataaddr = UInt(12.W)

}

class HAWINDOWSELFields extends Bundle {

  val reserved0 = UInt(17.W)

  /* The high bits of this field may be tied to 0, depending on how large
          the array mask register is.  E.g.\ on a hardware platform with 48 harts only bit 0
          of this field may actually be writable.
  */
  val hawindowsel = UInt(15.W)

}

class HAWINDOWFields extends Bundle {

  val maskdata = UInt(32.W)

}

class ABSTRACTCSFields extends Bundle {

  val reserved0 = UInt(3.W)

  /* Size of the Program Buffer, in 32-bit words. Valid sizes are 0 - 16.
  */
  val progbufsize = UInt(5.W)

  val reserved1 = UInt(11.W)

  /* 1: An abstract command is currently being executed.

            This bit is set as soon as \RdmCommand is written, and is
            not cleared until that command has completed.
  */
  val busy = Bool()

  /* This optional bit controls whether program buffer and abstract
           memory accesses are performed with the exact and full set of
           permission checks that apply based on the current architectural
           state of the hart performing the access, or with a relaxed set of
           permission checks (e.g. PMP restrictions are ignored).  The
           details of the latter are implementation-specific.  When set to 0,
           full permissions apply; when set to 1, relaxed permissions apply.
  */
  val relaxedpriv = Bool()

  /* Gets set if an abstract command fails. The bits in this field remain set until
            they are cleared by writing 1 to them. No abstract command is
            started until the value is reset to 0.

            This field only contains a valid value if \FdmAbstractcsBusy is 0.

            0 (none): No error.

            1 (busy): An abstract command was executing while \RdmCommand,
            \RdmAbstractcs, or \RdmAbstractauto was written, or when one
            of the {\tt data} or {\tt progbuf} registers was read or written.
            This status is only written if \FdmAbstractcsCmderr contains 0.

            2 (not supported): The command in \RdmCommand is not supported.  It
            may be supported with different options set, but it will not be
            supported at a later time when the hart or system state are
            different.

            3 (exception): An exception occurred while executing the command
            (e.g.\ while executing the Program Buffer).

            4 (halt/resume): The abstract command couldn't execute because the
            hart wasn't in the required state (running/halted), or unavailable.

            5 (bus): The abstract command failed due to a bus error (e.g.\ 
            alignment, access size, or timeout).

            6: Reserved for future use.

            7 (other): The command failed for another reason.
  */
  val cmderr = UInt(3.W)

  val reserved2 = UInt(4.W)

  /* Number of {\tt data} registers that are implemented as part of the
            abstract command interface. Valid sizes are 1 -- 12.
  */
  val datacount = UInt(4.W)

}

class COMMANDFields extends Bundle {

  /* The type determines the overall functionality of this
            abstract command.
  */
  val cmdtype = UInt(8.W)

  /* This field is interpreted in a command-specific manner,
            described for each abstract command.
  */
  val control = UInt(24.W)

}

class ABSTRACTAUTOFields extends Bundle {

  /* When a bit in this field is 1, read or write accesses to the
            corresponding {\tt progbuf} word cause the command in \RdmCommand to
            be executed again.
  */
  val autoexecprogbuf = UInt(16.W)

  val reserved0 = UInt(4.W)

  /* When a bit in this field is 1, read or write accesses to the
            corresponding {\tt data} word cause the command in \RdmCommand to be
            executed again.
  */
  val autoexecdata = UInt(12.W)

}

class CONFSTRPTR0Fields extends Bundle {

  val addr = UInt(32.W)

}

class CONFSTRPTR1Fields extends Bundle {

  val addr = UInt(32.W)

}

class CONFSTRPTR2Fields extends Bundle {

  val addr = UInt(32.W)

}

class CONFSTRPTR3Fields extends Bundle {

  val addr = UInt(32.W)

}

class NEXTDMFields extends Bundle {

  val addr = UInt(32.W)

}

class DATA0Fields extends Bundle {

  val data = UInt(32.W)

}

class PROGBUF0Fields extends Bundle {

  val data = UInt(32.W)

}

class AUTHDATAFields extends Bundle {

  val data = UInt(32.W)

}

class DMCS2Fields extends Bundle {

  val reserved0 = UInt(20.W)

  /* 0: The remaining fields in this register configure halt groups.

            1: The remaining fields in this register configure resume groups.
  */
  val grouptype = Bool()

  /* This field contains the currently selected DM external trigger.

            If a non-existent trigger value is written here, the hardware will
            change it to a valid one or 0 if no DM external triggers exist.
  */
  val dmexttrigger = UInt(4.W)

  /* When \FdmDmcsTwoHgselect is 0, contains the group of the hart
            specified by \Fhartsel.

            When \FdmDmcsTwoHgselect is 1, contains the group of the DM external
            trigger selected by \FdmDmcsTwoDmexttrigger.

            Writes only have an effect if \FdmDmcsTwoHgwrite is also written 1.

            Group numbers are contiguous starting at 0, with the highest number
            being implementation-dependent, and possibly different between
            different group types. Debuggers should read back this field after
            writing to confirm they are using a hart group that is supported.

            If groups aren't implemented, then this entire field is 0.
  */
  val group = UInt(5.W)

  /* When 1 is written and \FdmDmcsTwoHgselect is 0, for every selected
            hart the DM will change its group to the value written to \FdmDmcsTwoGroup,
            if the hardware supports that group for that hart.
            Implementations may also change the group of a minimal set of
            unselected harts in the same way, if that is necessary due to
            a hardware limitation.

            When 1 is written and \FdmDmcsTwoHgselect is 1, the DM will change
            the group of the DM external trigger selected by \FdmDmcsTwoDmexttrigger
            to the value written to \FdmDmcsTwoGroup, if the hardware supports
            that group for that trigger.

            Writing 0 has no effect.
  */
  val hgwrite = Bool()

  /* 0: Operate on harts.

            1: Operate on DM external triggers.

            If there are no DM external triggers, this field must be tied to 0.
  */
  val hgselect = Bool()

}

class HALTSUM0Fields extends Bundle {

  val haltsum0 = UInt(32.W)

}

class HALTSUM1Fields extends Bundle {

  val haltsum1 = UInt(32.W)

}

class HALTSUM2Fields extends Bundle {

  val haltsum2 = UInt(32.W)

}

class HALTSUM3Fields extends Bundle {

  val haltsum3 = UInt(32.W)

}

class SBCSFields extends Bundle {

  /* 0: The System Bus interface conforms to mainline drafts of this
            spec older than 1 January, 2018.

            1: The System Bus interface conforms to this version of the spec.

            Other values are reserved for future versions.
  */
  val sbversion = UInt(3.W)

  val reserved0 = UInt(6.W)

  /* Set when the debugger attempts to read data while a read is in
            progress, or when the debugger initiates a new access while one is
            already in progress (while \FdmSbcsSbbusy is set). It remains set until
            it's explicitly cleared by the debugger.

            While this field is set, no more system bus accesses can be
            initiated by the Debug Module.
  */
  val sbbusyerror = Bool()

  /* When 1, indicates the system bus master is busy. (Whether the
            system bus itself is busy is related, but not the same thing.) This
            bit goes high immediately when a read or write is requested for any
            reason, and does not go low until the access is fully completed.

            Writes to \RdmSbcs while \FdmSbcsSbbusy is high result in undefined
            behavior.  A debugger must not write to \RdmSbcs until it reads
            \FdmSbcsSbbusy as 0.
  */
  val sbbusy = Bool()

  /* When 1, every write to \RdmSbaddressZero automatically triggers a
            system bus read at the new address.
  */
  val sbreadonaddr = Bool()

  /* Select the access size to use for system bus accesses.

            0: 8-bit

            1: 16-bit

            2: 32-bit

            3: 64-bit

            4: 128-bit

            If \FdmSbcsSbaccess has an unsupported value when the DM starts a bus
            access, the access is not performed and \FdmSbcsSberror is set to 4.
  */
  val sbaccess = UInt(3.W)

  /* When 1, {\tt sbaddress} is incremented by the access size (in
            bytes) selected in \FdmSbcsSbaccess after every system bus access.
  */
  val sbautoincrement = Bool()

  /* When 1, every read from \RdmSbdataZero automatically triggers a
            system bus read at the (possibly auto-incremented) address.
  */
  val sbreadondata = Bool()

  /* When the Debug Module's system bus
            master encounters an error, this field gets set. The bits in this
            field remain set until they are cleared by writing 1 to them.
            While this field is non-zero, no more system bus accesses can be
            initiated by the Debug Module.

            An implementation may report ``Other'' (7) for any error condition.

            0: There was no bus error.

            1: There was a timeout.

            2: A bad address was accessed.

            3: There was an alignment error.

            4: An access of unsupported size was requested.

            7: Other.
  */
  val sberror = UInt(3.W)

  /* Width of system bus addresses in bits. (0 indicates there is no bus
            access support.)
  */
  val sbasize = UInt(7.W)

  /* 1 when 128-bit system bus accesses are supported.
  */
  val sbaccess128 = Bool()

  /* 1 when 64-bit system bus accesses are supported.
  */
  val sbaccess64 = Bool()

  /* 1 when 32-bit system bus accesses are supported.
  */
  val sbaccess32 = Bool()

  /* 1 when 16-bit system bus accesses are supported.
  */
  val sbaccess16 = Bool()

  /* 1 when 8-bit system bus accesses are supported.
  */
  val sbaccess8 = Bool()

}

class SBADDRESS0Fields extends Bundle {

  /* Accesses bits 31:0 of the physical address in {\tt sbaddress}.
  */
  val address = UInt(32.W)

}

class SBADDRESS1Fields extends Bundle {

  /* Accesses bits 63:32 of the physical address in {\tt sbaddress} (if
            the system address bus is that wide).
  */
  val address = UInt(32.W)

}

class SBADDRESS2Fields extends Bundle {

  /* Accesses bits 95:64 of the physical address in {\tt sbaddress} (if
            the system address bus is that wide).
  */
  val address = UInt(32.W)

}

class SBADDRESS3Fields extends Bundle {

  /* Accesses bits 127:96 of the physical address in {\tt sbaddress} (if
            the system address bus is that wide).
  */
  val address = UInt(32.W)

}

class SBDATA0Fields extends Bundle {

  /* Accesses bits 31:0 of {\tt sbdata}.
  */
  val data = UInt(32.W)

}

class SBDATA1Fields extends Bundle {

  /* Accesses bits 63:32 of {\tt sbdata} (if the system bus is that
            wide).
  */
  val data = UInt(32.W)

}

class SBDATA2Fields extends Bundle {

  /* Accesses bits 95:64 of {\tt sbdata} (if the system bus is that
            wide).
  */
  val data = UInt(32.W)

}

class SBDATA3Fields extends Bundle {

  /* Accesses bits 127:96 of {\tt sbdata} (if the system bus is that
            wide).
  */
  val data = UInt(32.W)

}

