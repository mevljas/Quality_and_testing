import pexpect2

def test_bst_save_restore():
    baza = pexpect2.pexpect()
 
    try:
        baza.expect("Enter command: ")

        baza.send("use sk")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Janez Levak 15 2111935500132")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Andrej Novak 15 2111935500138")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Janez Novak 15 2111935500112")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("2111935500112 | Novak, Janez | 15, 2111935500138 | Novak, Andrej | 15, 2111935500132 | Levak, Janez | 15")

        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("3")
        baza.expect("Enter command: ")

        baza.send("save test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("reset")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("0")
        baza.expect("Enter command: ")

        baza.send("restore test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("2111935500112 | Novak, Janez | 15, 2111935500138 | Novak, Andrej | 15, 2111935500132 | Levak, Janez | 15")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("3")
        baza.expect("Enter command: ")


        print "PASSED\ttest_bst_save_restore"

    except:
        print "FAILED\ttest_bst_save_restore"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_save_restore()

