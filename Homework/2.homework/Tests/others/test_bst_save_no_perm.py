import os, stat
import pexpect2
 

def test_bst_save_no_perm():
    baza = pexpect2.pexpect()
    filename = "p_test.bin"

    try:
        fd = open(filename, "w")
        fd.close()

        mode = os.stat(filename)[stat.ST_MODE]
        os.chmod(filename, mode & ~stat.S_IWRITE)

        baza.expect("Enter command: ")

        baza.send("use bst")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Andrej Novak 15 2111935500138")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Janez Levak 15 2111935500132")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("2111935500138 | Novak, Andrej | 15")
        baza.expect("\t2111935500132 | Levak, Janez | 15")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("save test.bin")
        baza.expect("Error: IO error p_test.bin (Access is denied)")
        baza.expect("Enter command: ")
        
        print "PASSED\ttest_bst_save_no_perm"

    except:
        print "FAILED\ttest_bst_save_no_perm"

    finally:
        baza.kill()
        os.chmod(filename, stat.S_IWRITE)
        os.remove(filename)


if __name__ == "__main__":
    test_bst_save_no_perm()

