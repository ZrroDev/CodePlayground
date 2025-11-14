#include <iostream>
#include <vector>
#include <string>

// int to Roman number converter
std::string intToRoman(int num) {

    std::vector<std::pair<int, std::string>> romanNumbers = {
        {1000, "M"},
        {900, "CM"},
        {500, "D"},
        {400, "CD"},
        {100, "C"},
        {90, "XC"},
        {50, "L"},
        {40, "XL"},
        {10, "X"},
        {9, "IX"},
        {5, "V"},
        {4, "IV"},
        {1, "I"}
    };

    std::string romanNum = "";
    int substract = num;

    for (const auto& [intNumber, simbol] : romanNumbers) {

        if (substract <= 0) break;
        
        while (substract >= intNumber) {
            substract -= intNumber;
            romanNum += simbol;
        }
    }
    return romanNum;
}
int main() {
    std::cout << intToRoman(1914) << std::endl;
}