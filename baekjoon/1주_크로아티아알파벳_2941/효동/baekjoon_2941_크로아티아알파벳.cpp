#include<iostream>
#include<string>

using namespace std;

int main() {
	string s;
	int cnt = 0;

	cin >> s;

	for (int i = 0; i < s.length(); i++) {
		//������ ������ �ص� ��¥�� ������ ���� ���ٽô� 
		//������ ũ�ξ�Ƽ�� ���ĺ��� �ƴϱ� ������ ��� ����
		char c = s[i];
		cnt++;

		if (i == s.length() - 1) break;

		if (s.length() > 2 && i<s.length()-2) {
			if (s[i] == 'd' && s[i + 1] == 'z' && s[i + 2] == '=') {
				i += 2;
				continue;
			}
		}

		switch (c) {
		case 'c':
			if (s[i + 1] == '=' || s[i + 1] == '-') i++; break;
		case 'd':
			if (s[i + 1] == '-') i++; break;			
		case 'l':
			if (s[i + 1] == 'j') i++; break;
		case 'n':
			if (s[i + 1] == 'j') i++; break;
		case 's':
			if (s[i + 1] == '=') i++; break;
		case 'z':
			if (s[i + 1] == '=') i++;
		}
	}

	cout << cnt << endl;
	return 0;
}